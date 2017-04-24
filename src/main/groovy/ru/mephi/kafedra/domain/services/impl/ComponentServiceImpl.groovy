package ru.mephi.kafedra.domain.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.mephi.kafedra.domain.DomainException
import ru.mephi.kafedra.domain.data.entities.Component
import ru.mephi.kafedra.domain.data.entities.Page
import ru.mephi.kafedra.domain.data.repositories.ComponentRepository
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

    @Override
    void createComponent(@NotNull Component component) {
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
        componentRepository.delete(id)
    }
}
