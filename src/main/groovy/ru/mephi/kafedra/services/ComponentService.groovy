package ru.mephi.kafedra.services

import ru.mephi.kafedra.data.entities.components.Component
import ru.mephi.kafedra.dto.ComponentDTO

/**
 * @author nivanov
 * on 26.12.16.
 */
interface ComponentService {
    Component createComponent(ComponentDTO component, Component parent)
    void removeComponent(Long id)
}