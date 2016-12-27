package ru.mephi.kafedra.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.mephi.kafedra.data.entities.components.Component
import ru.mephi.kafedra.data.entities.components.JsEventListener
import ru.mephi.kafedra.data.entities.components.Type
import ru.mephi.kafedra.data.repositories.ComponentRepository
import ru.mephi.kafedra.data.repositories.JsEventRepository
import ru.mephi.kafedra.data.repositories.SitePageRepository
import ru.mephi.kafedra.dto.ComponentDTO
import ru.mephi.kafedra.services.ComponentService

/**
 * @author nivanov
 * on 26.12.16.
 */
@Service
class ComponentServiceImpl implements ComponentService {

    @Autowired
    ComponentRepository repository

    @Autowired
    JsEventRepository jsEventRepository

    @Autowired
    SitePageRepository sitePageRepository

    @Override
    Component createComponent(ComponentDTO componentDTO, Component parent) {
        def component = new Component()
        if (parent != null)
            component.page = parent.page
        else if (componentDTO.pageId != null)
            sitePageRepository.findOne(componentDTO.pageId)
        component.jsAction = componentDTO.jsAction
        component.borderRadius = componentDTO.borderRadius
        component.colorHex = componentDTO.colorHex
        component.fontFamily = componentDTO.fontFamily
        component.fontSize = componentDTO.fontSize
        component.height = componentDTO.height
        component.width = componentDTO.width
        component.margin = componentDTO.margin
        component.padding = componentDTO.padding
        component.isHref = componentDTO.isHref
        component.position = componentDTO.position
        component.text = componentDTO.text
        component.parent = parent
        component.src = componentDTO.src
        component.type = Type.valueOf(componentDTO.type)
        component.children = new HashSet<>()
        if (componentDTO.children != null) {
            component.children = new HashSet<>()
            repository.save(component)
            componentDTO.children.forEach { childDTO ->
                if (childDTO.id != null && repository.exists(childDTO.id))
                    component.children.add(repository.findOne(childDTO.id))
                else
                    component.children.add(createComponent(childDTO, component))
            }
        }
        if (componentDTO.jsEventListeners != null) {
            component.jsEventListeners = new HashSet<>()
            componentDTO.jsEventListeners.forEach { event, jsCode ->
                repository.save(component)
                def jsEventListener = new JsEventListener()
                jsEventListener.component = component
                jsEventListener.event = event
                jsEventListener.js = jsCode
                component.jsEventListeners.add(jsEventListener)
                jsEventRepository.save(jsEventListener)
            }
        }
        repository.save(component)
        return component
    }


    @Override
    void removeComponent(Long id) {
        repository.delete(id)
    }
}
