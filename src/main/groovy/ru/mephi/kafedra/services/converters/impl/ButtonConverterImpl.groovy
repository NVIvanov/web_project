package ru.mephi.kafedra.services.converters.impl

import ru.mephi.kafedra.data.entities.components.ButtonComponent
import ru.mephi.kafedra.dto.ButtonDTO
import ru.mephi.kafedra.services.converters.Converter

/**
 * @author nivanov
 * on 26.12.16.
 */
class ButtonConverterImpl implements Converter<ButtonDTO, ButtonComponent> {


    @Override
    ButtonComponent convert(ButtonDTO dto) {
        def component = new ButtonComponent()
        component.jsAction = dto.jsAction
        return component
    }
}
