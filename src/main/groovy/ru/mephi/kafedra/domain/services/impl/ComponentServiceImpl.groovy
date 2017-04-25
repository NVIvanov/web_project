package ru.mephi.kafedra.domain.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.mephi.kafedra.domain.DomainException
import ru.mephi.kafedra.domain.data.entities.Attribute
import ru.mephi.kafedra.domain.data.entities.Component
import ru.mephi.kafedra.domain.data.entities.JsEventListener
import ru.mephi.kafedra.domain.data.entities.Page
import ru.mephi.kafedra.domain.data.repositories.AttributeRepository
import ru.mephi.kafedra.domain.data.repositories.ComponentRepository
import ru.mephi.kafedra.domain.data.repositories.JsEventListenersRepository
import ru.mephi.kafedra.domain.services.ComponentService
import ru.mephi.kafedra.domain.services.PageService

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 24.04.17.
 */

@Service
class ComponentServiceImpl implements ComponentService {

    @Autowired
    ComponentRepository componentRepository

    @Autowired
    PageService pageService

    @Autowired
    JsEventListenersRepository jsEventListenersRepository

    @Autowired
    AttributeRepository attributeRepository

    @Override
    void createComponent(@NotNull Component component) {
        if (component.id != null)
            deleteComponent(component.id)
        component.id = null
        componentRepository.save(component)
    }

    @Override
    Component getRootComponent(@NotNull String relativePath) {
        Optional<Page> pageOptional = pageService.getPageByPath(relativePath)
        Page page = pageOptional.orElseThrow { new DomainException(404, "page.not.found") }
        page.rootComponent
    }

    @Override
    Optional<Component> getComponentById(@NotNull Long id) {
        Optional.ofNullable(componentRepository.findOne(id))
    }

    @Override
    void deleteComponent(@NotNull Long id) {
        Optional<Component> componentOpt = getComponentById(id)
        Component component = componentOpt.orElseThrow { new DomainException(404, 'component.not.found') }
        component.children.forEach { child -> deleteComponent(child.id) }
        jsEventListenersRepository.delete(component.jsEventListeners)
        attributeRepository.delete(component.attributes)
        componentRepository.delete(id)
    }

    @Override
    void createAttribute(@NotNull Attribute attribute) {
        attributeRepository.save(attribute)
    }

    @Override
    void createJsEventListeners(@NotNull JsEventListener eventListener) {
        jsEventListenersRepository.save(eventListener)
    }
}
