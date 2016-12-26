package ru.mephi.kafedra.data.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.mephi.kafedra.data.entities.Site

/**
 * @author nivanov
 * on 26.12.16.
 */
@Repository
interface SiteRepository extends CrudRepository<Site, Long> {
    Collection<Site> findByOwnerUsername(String username)

    Optional<Site> findByRelativePath(String relativePath)
}