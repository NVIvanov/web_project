package ru.mephi.kafedra.web.facades

import ru.mephi.kafedra.web.dto.PageDTO

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 25.04.17.
 */
interface PageFacade {
    def createPage(@NotNull PageDTO dto)

    def getPage(@NotNull Long id)

    def updatePage(@NotNull PageDTO dto)

    def deletePage(@NotNull Long id)
}