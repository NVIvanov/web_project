package ru.mephi.kafedra.domain.services

import ru.mephi.kafedra.domain.data.entities.Attribute
import ru.mephi.kafedra.domain.data.entities.Component
import ru.mephi.kafedra.domain.data.entities.JsEventListener

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 24.04.17.
 */
interface ComponentService {
    void createComponent(@NotNull Component component)

    @NotNull
    Component getRootComponent(@NotNull String relativePath)

    Optional<Component> getComponentById(@NotNull Long id)

    void deleteComponent(@NotNull Long id)

    void createAttribute(@NotNull Attribute attribute)

    void createJsEventListeners(@NotNull JsEventListener eventListener)
}
