package ru.mephi.kafedra.domain.services

import ru.mephi.kafedra.domain.data.entities.Page
import ru.mephi.kafedra.domain.data.entities.Site

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 24.04.17.
 */
interface PageService {
    void createPage(@NotNull Page page)

    @NotNull
    Set<Page> getPagesForSite(@NotNull Site site)

    @NotNull
    Optional<Page> getPageByPath(@NotNull String path)

    @NotNull
    Optional<Page> getPageById(@NotNull Long id)

    @NotNull
    Page updatePage(@NotNull Page pageToUpdate)

    void deletePage(Long id)
}
