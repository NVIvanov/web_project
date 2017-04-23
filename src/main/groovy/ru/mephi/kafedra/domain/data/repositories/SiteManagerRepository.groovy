package ru.mephi.kafedra.domain.data.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.mephi.kafedra.domain.data.entities.Site
import ru.mephi.kafedra.domain.data.entities.SiteManager
import ru.mephi.kafedra.domain.data.entities.SystemUser

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 23.04.17.
 */

@Repository
interface SiteManagerRepository extends CrudRepository<SiteManager, Long> {
    Set<SiteManager> findBySite(@NotNull Site site)

    Set<SiteManager> findByUser(@NotNull SystemUser user)
}
