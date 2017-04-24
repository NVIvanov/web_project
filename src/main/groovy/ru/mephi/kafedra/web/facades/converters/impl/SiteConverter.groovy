package ru.mephi.kafedra.web.facades.converters.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.mephi.kafedra.domain.data.entities.Site
import ru.mephi.kafedra.domain.services.SiteService
import ru.mephi.kafedra.web.dto.SiteDTO
import ru.mephi.kafedra.web.facades.converters.Converter

/**
 * @author nivanov
 * on 24.04.17.
 */

@Component
class SiteConverter implements Converter<SiteDTO, Site> {

    @Autowired
    SiteService siteService

    @Override
    Site convertWithExistModel(Site site, SiteDTO siteDTO) {
        if (site == null)
            site = new Site()
        if (siteDTO.title != null)
            site.title = siteDTO.title
        if (siteDTO.description != null)
            site.description = siteDTO.description
        if (siteDTO.relativePath != null)
            site.relativePath = site.relativePath
        return site
    }

    @Override
    SiteDTO convert(Site site) {
        SiteDTO dto = new SiteDTO()
        dto.relativePath = site.relativePath
        dto.description = site.description
        dto.title = site.title
        dto.id = site.id
        dto.currentUserRole = siteService.getCurrentUserRoleInSite(site).name()
        return dto
    }
}
