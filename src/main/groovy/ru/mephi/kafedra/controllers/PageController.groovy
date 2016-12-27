package ru.mephi.kafedra.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.mephi.kafedra.services.ComponentService
import ru.mephi.kafedra.services.PageService

/**
 * @author nivanov
 * on 27.12.16.
 */
@RestController
@RequestMapping("/{path}")
class PageController {

    @Autowired
    PageService pageService

    @Autowired
    ComponentService componentService

    @GetMapping("/")
    String getView(@PathVariable String path) {
        pageService.getPage(path).get(0)
    }

    @GetMapping("/edit")
    String getEditView(@PathVariable String path, Model model) {
        model.addAttribute("component", componentService
                .getComponents(pageService.getPage(path).get(0)))
    }
}
