package ru.mephi.kafedra.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.mephi.kafedra.data.entities.SitePage
import ru.mephi.kafedra.data.repositories.SitePageRepository
import ru.mephi.kafedra.dto.SitePageDTO
import ru.mephi.kafedra.services.PageService

import java.util.stream.Collectors

/**
 * @author nivanov
 * on 27.12.16.
 */

@Service
class PageServiceImpl implements PageService {
    @Autowired
    SitePageRepository pageRepository

    @Override
    SitePage createPage(SitePageDTO dto) {
        def page = new SitePage()
        if (dto.parent != null)
            page.parentPage = pageRepository.findOne(dto.parent)
        page.relativePath = dto.relativePath
        if (dto.children != null) {
            page.children = new HashSet<>()
            dto.children.forEach { id ->
                page.children.add(pageRepository.findOne(id))
            }
        }
        pageRepository.save(page)
        return page
    }

    List<SitePageDTO> getPage(String relativePath) {
        pageRepository.findByRelativePath(relativePath)
                .stream()
                .map { model ->
            def dto = new SitePageDTO()
            dto.relativePath = model.relativePath
            dto.id = model.id
            if (model.children != null)
                dto.children = model.children.stream().map { child ->
                    return child.id
                }.collect(Collectors.toList())
            if (model.parentPage != null)
                dto.parent = model.parentPage.id
            return dto
        }.collect(Collectors.toList())
    }
}
