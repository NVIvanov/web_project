package ru.mephi.kafedra.web.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.mephi.kafedra.web.dto.SiteDTO
import ru.mephi.kafedra.web.facades.SiteFacade

import javax.validation.Valid

/**
 * @author nivanov
 * on 24.04.17.
 */

@Controller
@RequestMapping("/sites")
class SiteController {

    @Autowired
    private SiteFacade siteFacade

    @GetMapping
    def getSiteForUser() {
        siteFacade.getSites()
    }

    @GetMapping("/{path}")
    def getSiteById(@PathVariable String path) {
        siteFacade.getSite(path)
    }

    @PostMapping
    def createSite(@RequestBody @Valid SiteDTO siteDTO) {
        siteFacade.createSite(siteDTO)
    }

    @PutMapping
    def updateSite(@RequestBody @Valid SiteDTO siteDTO) {
        siteFacade.updateSite(siteDTO)
    }

    @DeleteMapping
    def deleteSite(@RequestParam Long id) {
        siteFacade.deleteSite(id)
    }

    @PostMapping("/{id}/grant")
    def grantRole(@PathVariable Long id, @RequestParam String user, @RequestParam String role) {
        siteFacade.grantRole(id, user, role)
    }

    @PostMapping("/{id}/revoke")
    def revokeRole(@PathVariable Long id, @RequestParam String user) {
        siteFacade.revokeRole(id, user)
    }
}
