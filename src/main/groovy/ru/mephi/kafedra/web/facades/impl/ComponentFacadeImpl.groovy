package ru.mephi.kafedra.web.facades.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.mephi.kafedra.domain.data.entities.Component
import ru.mephi.kafedra.domain.services.ComponentService
import ru.mephi.kafedra.web.dto.ComponentDTO
import ru.mephi.kafedra.web.facades.ComponentFacade
import ru.mephi.kafedra.web.facades.converters.Converter

/**
 * @author nivanov
 * on 25.04.17.
 */

@Service
class ComponentFacadeImpl implements ComponentFacade {

    @Autowired
    ComponentService componentService

    @Autowired
    Converter<ComponentDTO, Component> componentConverter

    @Override
    void saveComponent(ComponentDTO dto) {
        componentService.createComponent(componentConverter.convertWithExistModel(null, dto))
    }
}
