package ru.mephi.kafedra.domain.services

import ru.mephi.kafedra.domain.data.entities.Component

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 24.04.17.
 */
interface ComonentService {
    void createComponent(@NotNull Component component)

    @NotNull
    Component getRootComponent(@NotNull String relativePath)

    void deleteComponent(@NotNull Long id)
}
