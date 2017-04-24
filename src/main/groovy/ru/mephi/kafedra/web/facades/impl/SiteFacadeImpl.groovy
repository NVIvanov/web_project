package ru.mephi.kafedra.web.facades.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.mephi.kafedra.domain.DomainException
import ru.mephi.kafedra.domain.data.entities.Site
import ru.mephi.kafedra.domain.data.entities.SiteManager
import ru.mephi.kafedra.domain.services.SiteService
import ru.mephi.kafedra.web.dto.SiteDTO
import ru.mephi.kafedra.web.facades.SiteFacade
import ru.mephi.kafedra.web.facades.converters.Converter

import javax.validation.constraints.NotNull

import static java.util.stream.Collectors.toList

/**
 * @author nivanov
 * on 24.04.17.
 */

@Service
class SiteFacadeImpl implements SiteFacade {

    @Autowired
    private SiteService siteService

    @Autowired
    private Converter<SiteDTO, Site> siteConverter

    @Override
    Set<SiteDTO> getSites() {
        siteService.getSitesForCurrentUser()
                .stream()
                .map { site -> siteConverter.convert(site) }
                .collect(toList())
    }

    @Override
    SiteDTO getSite(String relativePath) {
        Optional<Site> siteOptional = siteService.getSiteByRelativePath(relativePath)
        Site site = siteOptional.orElseThrow { new DomainException(404, 'site.not.found') }
        siteConverter.convert(site)
    }

    @Override
    void createSite(SiteDTO dto) {
        def site = siteConverter.convertWithExistModel(null, dto)
        siteService.createSite(site)
    }

    @Override
    SiteDTO updateSite(SiteDTO dto) {
        def siteOpt = siteService.getSiteById(dto.id)
        def site = siteOpt.orElseThrow { new DomainException(404, "site.not.found") }
        site = siteConverter.convertWithExistModel(site, dto)
        site = siteService.updateSite(site)
        siteConverter.convert(site)
    }

    @Override
    void deleteSite(@NotNull Long id) {
        siteService.deleteSite(id)
    }

    @Override
    void grantRole(@NotNull Long siteId, @NotNull String username, @NotNull String role) {
        siteService.grantRole(siteId, username, SiteManager.Role.valueOf(role))
    }

    @Override
    void revokeRole(@NotNull Long siteId, @NotNull String username) {
        siteService.revokeRole(siteId, username)
    }
}
