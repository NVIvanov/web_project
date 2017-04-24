package ru.mephi.kafedra.domain.data.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.mephi.kafedra.domain.data.entities.Page
import ru.mephi.kafedra.domain.data.entities.Site

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 23.04.17.
 */

@Repository
interface PageRepository extends CrudRepository<Page, Long> {
    Set<Page> findByParentPage(@NotNull Page page)

    Optional<Page> findByRelativePathAndSite(@NotNull String relativePath, @NotNull Site site)
}