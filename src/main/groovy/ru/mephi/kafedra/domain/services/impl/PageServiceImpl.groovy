package ru.mephi.kafedra.domain.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mephi.kafedra.domain.data.entities.Page
import ru.mephi.kafedra.domain.data.entities.Site
import ru.mephi.kafedra.domain.data.repositories.PageRepository
import ru.mephi.kafedra.domain.data.repositories.SiteRepository
import ru.mephi.kafedra.domain.services.PageService

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 24.04.17.
 */

@Service
@Transactional
class PageServiceImpl implements PageService {

    @Autowired
    PageRepository pageRepository

    @Autowired
    SiteRepository siteRepository

    @Override
    void createPage(@NotNull Page page) {
        pageRepository.save(page)
    }

    @Override
    Set<Page> getPagesForSite(@NotNull Site site) {
        return site.pages
    }

    @Override
    Optional<Page> getPageByPath(@NotNull String path) {
        String[] paths = path.split("/")
        if (paths.length < 2)
            throw new IllegalArgumentException('Wrong argument format')
        String siteRelativePath = paths[0], pageRelativePath = paths[1]
        Optional<Site> siteOptional = siteRepository.findByRelativePath(siteRelativePath)
        if (!siteOptional.isPresent())
            throw new IllegalArgumentException('Wrong argument value')
        Site site = siteOptional.get()
        return pageRepository.findByRelativePathAndSite(pageRelativePath, site)
    }

    @Override
    Page updatePage(@NotNull Page pageToUpdate) {
        pageRepository.save(pageToUpdate)
        return pageToUpdate
    }

    @Override
    void deletePage(Long id) {
        pageRepository.delete(id)
    }
}
