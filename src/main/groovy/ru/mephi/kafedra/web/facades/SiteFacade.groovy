package ru.mephi.kafedra.web.facades

import ru.mephi.kafedra.web.dto.SiteDTO

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 24.04.17.
 */
interface SiteFacade {
    Set<SiteDTO> getSites()

    SiteDTO getSite(String relativePath)

    void createSite(SiteDTO dto)

    SiteDTO updateSite(SiteDTO dto)

    void deleteSite(@NotNull Long id)

    void grantRole(@NotNull Long siteId, @NotNull String username, @NotNull String role)

    void revokeRole(@NotNull Long siteId, @NotNull String username)
}
