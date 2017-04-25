package ru.mephi.kafedra.web.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.mephi.kafedra.web.dto.PageDTO
import ru.mephi.kafedra.web.facades.ComponentFacade
import ru.mephi.kafedra.web.facades.PageFacade

/**
 * @author nivanov
 * on 25.04.17.
 */

@RestController
@RequestMapping("/edit/{id}")
class ComponentController {

    @Autowired
    PageFacade pageFacade

    @Autowired
    ComponentFacade componentFacade

    @GetMapping
    def getComponents(@PathVariable Long pageId) {
        PageDTO pageDTO = pageFacade.getPage(pageId)
        pageFacade.
    }
}
