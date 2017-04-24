package ru.mephi.kafedra.web.facades.converters.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.mephi.kafedra.domain.DomainException
import ru.mephi.kafedra.domain.data.entities.Page
import ru.mephi.kafedra.domain.data.entities.Site
import ru.mephi.kafedra.domain.services.ComponentService
import ru.mephi.kafedra.domain.services.PageService
import ru.mephi.kafedra.domain.services.SiteService
import ru.mephi.kafedra.web.dto.PageDTO
import ru.mephi.kafedra.web.facades.converters.Converter

import static java.util.stream.Collectors.toList

/**
 * @author nivanov
 * on 24.04.17.
 */

@Component
class PageConverter implements Converter<PageDTO, Page> {

    @Autowired
    private PageService pageService

    @Autowired
    private SiteService siteService

    @Autowired
    private ComponentService componentService

    @Override
    Page convertWithExistModel(Page page, PageDTO pageDTO) {
        if (page == null)
            page = new Page()
        if (pageDTO.siteId != null) {
            def siteOpt = siteService.getSiteById(pageDTO.siteId)
            def site = siteOpt.orElseThrow { new DomainException(404, 'site.not.found') }
            page.site = site
        }
        if (pageDTO.relativePath != null)
            page.relativePath = pageDTO.relativePath
        if (pageDTO.title != null)
            page.title = pageDTO.title
        if (pageDTO.description != null)
            page.description = pageDTO.description
        if (pageDTO.children != null) {
            Set<Page> children = pageDTO.children.stream()
                    .map { childDTO -> pageService.getPageByPath(childDTO.relativePath) }
                    .filter { opt -> opt.isPresent() }
                    .map { opt -> opt.get() }
                    .collect(toList())
            page.children = children
        }
        if (pageDTO.siteId != null) {
            Optional<Site> siteOptional = siteService.getSiteById(pageDTO.siteId)
            Site site = siteOptional.orElseThrow { new DomainException(404, 'site.not.found') }
            page.site = site
        }
        if (pageDTO.parentId != null) {
            Optional<Page> pageOptional = pageService.getPageById(pageDTO.parentId)
            Page parentPage = pageOptional.orElseThrow { new DomainException(404, 'page.not.found') }
            page.parentPage = parentPage
        }
        if (pageDTO.rootComponentId != null) {
            Optional<ru.mephi.kafedra.domain.data.entities.Component> componentOptional =
                    componentService.getComponentById(pageDTO.rootComponentId)
            ru.mephi.kafedra.domain.data.entities.Component component = componentOptional
                    .orElseThrow { new DomainException(404, 'component.not.found') }
            page.rootComponent = component
        }
        page
    }

    @Override
    PageDTO convert(Page page) {
        def dto = new PageDTO()
        dto.rootComponentId = page.rootComponent.id
        dto.id = page.id
        dto.parentId = page.parentPage.id
        dto.siteId = page.site.id
        dto.children = page.children.stream()
                .map { p -> convert(p) }
                .collect(toList())
        dto.relativePath = page.relativePath
        dto.description = page.description
        dto.title = page.title
        dto
    }
}
