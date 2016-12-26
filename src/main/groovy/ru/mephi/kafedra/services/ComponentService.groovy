package ru.mephi.kafedra.services

import ru.mephi.kafedra.dto.ButtonDTO

/**
 * @author nivanov
 * on 26.12.16.
 */
interface ComponentService {
    void createButton(ButtonDTO component)

    void removeComponent(Long id)
}