package ru.mephi.kafedra.domain.services

import ru.mephi.kafedra.domain.data.entities.Site
import ru.mephi.kafedra.domain.data.entities.SiteManager.Role

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 23.04.17.
 */

interface SiteService {
    void createSite(@NotNull Site site)
    Optional<Site> getSiteById(@NotNull Long id)

    Optional<Site> getSiteByRelativePath(@NotNull String relativePath)
    @NotNull
    Set<Site> getSitesForCurrentUser()
    @NotNull
    Site updateSite(@NotNull Site siteToUpdate)

    @NotNull
    Role getCurrentUserRoleInSite(@NotNull Site site)

    void deleteSite(@NotNull Long id)
    void grantRole(@NotNull Long siteId, @NotNull String user, @NotNull Role role)
    void revokeRole(@NotNull Long siteId, @NotNull String user)
}