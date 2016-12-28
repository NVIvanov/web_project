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
import ru.mephi.kafedra.dto.SitePageDTO
import ru.mephi.kafedra.services.ComponentService

import java.util.function.Function
import java.util.stream.Collectors

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
            component.page = sitePageRepository.findOne(componentDTO.pageId)
        def page = component.page
        if (page.components == null)
            page.components = new HashSet<>()
        if (parent == null) {
            page.components.add(component)
        }
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
        sitePageRepository.save(page)
        return component
    }

    @Override
    List<ComponentDTO> getComponents(SitePageDTO page) {
        repository.findByPageRelativePathAndPageParentPageId(page.relativePath, page.parent)
                .stream()
                .map { model ->
            convertComponent(model)
        }.collect(Collectors.toList())
    }

    private ComponentDTO convertComponent(Component model) {
        def dto = new ComponentDTO()
        dto.id = model.id
        dto.borderRadius = model.borderRadius
        if (model.children != null)
            dto.children = model.children.stream().map { child ->
                convertComponent(child)
            }.collect(Collectors.toList())
        dto.colorHex = model.colorHex
        dto.fontFamily = model.fontFamily
        dto.fontSize = model.fontSize
        dto.height = model.height
        dto.width = model.width
        dto.padding = model.padding
        dto.margin = model.margin
        dto.isHref = model.isHref
        if (model.jsEventListeners != null)
            dto.jsEventListeners = model.jsEventListeners.stream().collect(Collectors.toMap(new Function() {
                @Override
                Object apply(Object o) {
                    return ((JsEventListener) o).event
                }
            }, new Function() {
                @Override
                Object apply(Object o) {
                    return ((JsEventListener) o).js
                }
            }))
        dto.position = model.position
        if (model.page != null)
            dto.pageId = model.page.id
        dto.src = model.src
        dto.text = model.text
        dto.type = model.type
        return dto
    }

    @Override
    void removeComponent(Long id) {
        repository.delete(id)
    }

    @Override
    void removeComponentOnPage(Long id) {
        def page = sitePageRepository.findOne(id)
        if (page.components != null) {
            page.components.forEach { component -> repository.delete(component) }
            page.components.clear()
        }
        sitePageRepository.save(page)
    }
}
