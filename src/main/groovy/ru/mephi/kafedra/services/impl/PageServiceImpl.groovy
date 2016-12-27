package ru.mephi.kafedra.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.mephi.kafedra.data.entities.SitePage
import ru.mephi.kafedra.data.repositories.SitePageRepository
import ru.mephi.kafedra.dto.SitePageDTO
import ru.mephi.kafedra.services.PageService

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
}
