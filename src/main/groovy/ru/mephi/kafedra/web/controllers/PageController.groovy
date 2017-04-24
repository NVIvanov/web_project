package ru.mephi.kafedra.web.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.mephi.kafedra.web.dto.PageDTO
import ru.mephi.kafedra.web.facades.PageFacade

import javax.validation.Valid

/**
 * @author nivanov
 * on 25.04.17.
 */

@Controller
@RequestMapping("/sites/{id}/pages")
class PageController {

    @Autowired
    private PageFacade pageFacade

    @PostMapping
    def createPage(@RequestBody @Valid PageDTO dto) {
        pageFacade.createPage(dto)
    }

    @GetMapping("/{id}")
    def getPage(@PathVariable Long pageId) {
        pageFacade.getPage(pageId)
    }

    @PutMapping
    def updatePage(@RequestBody @Valid PageDTO dto) {
        pageFacade.updatePage(dto)
    }

    @DeleteMapping
    def deletePage(@RequestParam Long id) {
        pageFacade.deletePage(id)
    }
}
