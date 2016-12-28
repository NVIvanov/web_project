package ru.mephi.kafedra.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.mephi.kafedra.dto.SitePageDTO
import ru.mephi.kafedra.services.PageService

/**
 * @author nivanov
 * on 28.12.16.
 */
@RestController
@RequestMapping("/{path}")
class PageRestController {

    @Autowired
    PageService pageService

    @GetMapping(value = "/get", produces = "application/json")
    SitePageDTO getInfo(@PathVariable String path) {
        List<SitePageDTO> pageDTOs = pageService.getPage(path)
        return pageDTOs.size() > 0 ? pageDTOs.get(0) : null
    }
}
