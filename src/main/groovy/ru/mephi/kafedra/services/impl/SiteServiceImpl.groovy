package ru.mephi.kafedra.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.mephi.kafedra.data.entities.Site
import ru.mephi.kafedra.data.repositories.SiteRepository
import ru.mephi.kafedra.data.repositories.UserRepository
import ru.mephi.kafedra.dto.SiteDTO
import ru.mephi.kafedra.dto.SitePageDTO
import ru.mephi.kafedra.services.PageService
import ru.mephi.kafedra.services.SiteService

/**
 * @author nivanov
 * on 27.12.16.
 */

@Service
class SiteServiceImpl implements SiteService {

    @Autowired
    SiteRepository siteRepository

    @Autowired
    UserRepository userRepository

    @Autowired
    PageService pageService

    @Override
    void createSite(SiteDTO dto) {
        def site = new Site()
        def mainPage = new SitePageDTO()
        mainPage.relativePath = dto.relativePath
        site.mainPage = pageService.createPage(mainPage)
        site.owner = userRepository.findByUsername(dto.owner).orElseThrow {
            throw new IllegalStateException()
        }
    }
}
