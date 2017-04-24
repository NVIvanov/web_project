package ru.mephi.kafedra.web.facades.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.mephi.kafedra.domain.DomainException
import ru.mephi.kafedra.domain.data.entities.Page
import ru.mephi.kafedra.domain.services.PageService
import ru.mephi.kafedra.web.dto.PageDTO
import ru.mephi.kafedra.web.facades.PageFacade
import ru.mephi.kafedra.web.facades.converters.Converter

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 25.04.17.
 */

@Service
class PageFacadeImpl implements PageFacade {

    @Autowired
    private PageService pageService

    @Autowired
    private Converter<PageDTO, Page> pageConverter

    @Override
    def createPage(@NotNull PageDTO dto) {
        pageService.createPage(pageConverter.convertWithExistModel(null, dto))
    }

    @Override
    def getPage(@NotNull Long id) {
        pageService.getPageById(id).orElseThrow { new DomainException(404, 'page.not.found') }
    }

    @Override
    def updatePage(@NotNull PageDTO dto) {
        Optional<Page> pageOptional = pageService.getPageById(dto.id)
        Page page = pageOptional.orElseThrow { new DomainException(404, 'page.not.found') }
        page = pageConverter.convertWithExistModel(page, dto)
        page = pageService.updatePage(page)
        pageConverter.convert(page)
    }

    @Override
    def deletePage(@NotNull Long id) {
        pageService.deletePage(id)
    }
}
