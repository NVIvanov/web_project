package ru.mephi.kafedra.domain.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mephi.kafedra.domain.DomainException
import ru.mephi.kafedra.domain.data.entities.Site
import ru.mephi.kafedra.domain.data.entities.SiteManager
import ru.mephi.kafedra.domain.data.entities.SystemUser
import ru.mephi.kafedra.domain.data.repositories.SiteManagerRepository
import ru.mephi.kafedra.domain.data.repositories.SiteRepository
import ru.mephi.kafedra.domain.services.SiteService
import ru.mephi.kafedra.domain.services.UserService

import javax.validation.constraints.NotNull

import static java.util.stream.Collectors.toList

/**
 * @author nivanov
 * on 23.04.17.
 */

@Service
@Transactional
class SiteServiceImpl implements SiteService {

    @Autowired
    SiteRepository siteRepository

    @Autowired
    SiteManagerRepository siteManagerRepository

    @Autowired
    UserService userService

    @Override
    void createSite(@NotNull Site site) {
        site.id = null
        siteRepository.save(site)
        SystemUser currentUser = userService.currentLoggedUser()
        SiteManager siteAdministrator = new SiteManager()
        siteAdministrator.site = site
        siteAdministrator.user = currentUser
        siteAdministrator.role = SiteManager.Role.ADMIN
        siteManagerRepository.save(siteAdministrator)
    }

    @Override
    Optional<Site> getSiteById(@NotNull Long id) {
        return Optional.ofNullable(siteRepository.findOne(id))
    }

    @Override
    Set<Site> getSitesForCurrentUser() {
        SystemUser currentUser = userService.currentLoggedUser()
        return siteManagerRepository.findByUser(currentUser).stream()
                .map { manager -> manager.getSite() }
                .collect(toList())
    }

    @Override
    Site updateSite(@NotNull Site siteToUpdate) {
        if (siteToUpdate.id == null) {
            throw new IllegalArgumentException("site must have id")
        }
        if (!siteRepository.exists(siteToUpdate.id)) {
            throw new DomainException(400, "site.not.exist")
        }
        siteRepository.save(siteToUpdate)
        return siteToUpdate
    }

    @Override
    void deleteSite(@NotNull Long id) {
        if (!siteRepository.exists(id))
            throw new DomainException(404, "site.not.exist")
        siteRepository.delete(id)
    }

    @Override
    void grantRole(@NotNull Long siteId, @NotNull String username, @NotNull SiteManager.Role role) {
        Site site = siteRepository.findOne(siteId)
        Set<SiteManager> managers = site.managers
        if (managers.stream().filter { manager -> manager.role == SiteManager.Role.ADMIN }
                .noneMatch { manager ->
            userService.permitOperationToCurrentLoggedUser(manager.user.username)
        }) {
            throw new DomainException(403, "user.have.not.right")
        }
        if (site == null) {
            throw new DomainException(404, "site.not.found")
        }
        Optional<SystemUser> userOpt = userService.findUser(username)
        if (!userOpt.isPresent()) {
            throw new DomainException(404, "user.not.found")
        }
        SiteManager manager = new SiteManager()
        manager.user = userOpt.get()
        manager.site = site
        manager.role = role
        siteManagerRepository.save(manager)
    }

    @Override
    void revokeRole(@NotNull Long siteId, @NotNull String username) {
        Site site = siteRepository.findOne(siteId)
        Set<SiteManager> managers = site.managers
        if (managers.stream().filter { manager -> manager.role == SiteManager.Role.ADMIN }
                .noneMatch { manager ->
            userService.permitOperationToCurrentLoggedUser(manager.user.username)
        }) {
            throw new DomainException(403, "user.have.not.right")
        }
        if (site == null) {
            throw new DomainException(404, "site.not.found")
        }
        Optional<SystemUser> userOpt = userService.findUser(username)
        if (!userOpt.isPresent()) {
            throw new DomainException(404, "user.not.found")
        }
        Optional<SiteManager> managerOpt = siteManagerRepository.findByUserAndSite(userOpt.get(), site)
        SiteManager manager = managerOpt.orElseThrow { new DomainException(404, "user.not.found") }
        siteManagerRepository.delete(manager)
    }
}
