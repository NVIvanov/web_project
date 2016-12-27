package ru.mephi.kafedra.data.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.mephi.kafedra.data.entities.SitePage

/**
 * @author nivanov
 * on 26.12.16.
 */
@Repository
interface SitePageRepository extends CrudRepository<SitePage, Long> {
    Optional<SitePage> findByRelativePathAndParentPageRelativePath(String relativePath, String parentRelativePath)

    Optional<SitePage> findByChildrenId(Long id)
    List<SitePage> findByRelativePath(String relativePath)
}