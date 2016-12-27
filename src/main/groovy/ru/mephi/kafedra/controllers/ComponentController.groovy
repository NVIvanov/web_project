package ru.mephi.kafedra.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.mephi.kafedra.dto.ComponentDTO
import ru.mephi.kafedra.services.ComponentService

import javax.validation.Valid

/**
 * @author nivanov
 * on 27.12.16.
 */
@RestController
@RequestMapping("/components")
class ComponentController {

    @Autowired
    ComponentService componentService

    @PostMapping("/")
    void createComponents(@Valid ComponentDTO componentDTO) {
        componentService.removeComponentOnPage(componentDTO.pageId)
        componentService.createComponent(componentDTO, null)
    }
}