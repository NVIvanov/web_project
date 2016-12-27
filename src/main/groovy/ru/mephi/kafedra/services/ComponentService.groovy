package ru.mephi.kafedra.services

import ru.mephi.kafedra.data.entities.components.Component
import ru.mephi.kafedra.dto.ComponentDTO
import ru.mephi.kafedra.dto.SitePageDTO

/**
 * @author nivanov
 * on 26.12.16.
 */
interface ComponentService {
    Component createComponent(ComponentDTO component, Component parent)

    List<ComponentDTO> getComponents(SitePageDTO page)
    void removeComponent(Long id)

    void removeComponentOnPage(Long id)
}