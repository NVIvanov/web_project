package ru.mephi.kafedra.services

import ru.mephi.kafedra.data.entities.SitePage
import ru.mephi.kafedra.dto.SitePageDTO

/**
 * @author nivanov
 * on 27.12.16.
 */
interface PageService {
    SitePage createPage(SitePageDTO dto)

    List<SitePageDTO> getPage(String relativePath)
}