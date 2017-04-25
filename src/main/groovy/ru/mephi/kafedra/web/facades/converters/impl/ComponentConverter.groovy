package ru.mephi.kafedra.web.facades.converters.impl

import org.springframework.beans.factory.annotation.Autowired
import ru.mephi.kafedra.domain.data.entities.Attribute
import ru.mephi.kafedra.domain.data.entities.Component
import ru.mephi.kafedra.domain.data.entities.JsEventListener
import ru.mephi.kafedra.domain.services.ComponentService
import ru.mephi.kafedra.web.dto.ComponentDTO
import ru.mephi.kafedra.web.facades.converters.Converter

import static java.util.stream.Collectors.toList

/**
 * @author nivanov
 * on 25.04.17.
 */

@org.springframework.stereotype.Component
class ComponentConverter implements Converter<ComponentDTO, Component> {

    @Autowired
    private ComponentService componentService

    @Override
    Component convertWithExistModel(Component component, ComponentDTO componentDTO) {
        if (component == null)
            component = new Component()
        if (componentDTO.id != null)
            component.id = componentDTO.id
        if (componentDTO.type != null)
            component.type = Component.Type.valueOf(componentDTO.type)
        if (componentDTO.children != null) {
            Set<Component> children = componentDTO.children.stream()
                    .map { child -> componentService.createComponent(convertWithExistModel(null, child)) }
                    .collect(toList())
            component.children = children
        }
        if (componentDTO.jsEventListeners != null) {
            Set<JsEventListener> eventListeners = new HashSet<>()
            componentDTO.jsEventListeners.forEach { event, code ->
                JsEventListener eventListener = new JsEventListener()
                eventListener.code = code
                eventListener.event = event
                componentService.createJsEventListeners(eventListener)
                eventListeners.add(eventListener)
            }
            component.jsEventListeners = eventListeners
        }
        if (componentDTO.attributes != null) {
            Set<Attribute> attributes = new HashSet<>()
            componentDTO.attributes.forEach { key, value ->
                Attribute attribute = new Attribute()
                attribute.value = value
                attribute.key = key
                componentService.createAttribute(attribute)
                attributes.add(attribute)
            }
            component.attributes = attributes
        }
        if (componentDTO.title != null)
            component.title = componentDTO.title
        component
    }

    @Override
    ComponentDTO convert(Component component) {
        def dto = new ComponentDTO()
        dto.pageId = component.placement.id
        dto.title = component.title
        dto.children = component.children.forEach { model -> convert(model) }
        Map<String, String> map = new HashMap<>()
        component.attributes.forEach { attribute ->
            map.put(attribute.key, attribute.value)
        }
        dto.attributes = map
        map = new HashMap<>()
        component.jsEventListeners.forEach { eventListener ->
            map.put(eventListener.event, eventListener.code)
        }
        dto.jsEventListeners = map
        dto.type = component.type.name()
        dto
    }
}
