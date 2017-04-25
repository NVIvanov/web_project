package ru.mephi.kafedra.web.facades

import ru.mephi.kafedra.web.dto.ComponentDTO

/**
 * @author nivanov
 * on 25.04.17.
 */
interface ComponentFacade {
    void saveComponent(ComponentDTO dto)
}