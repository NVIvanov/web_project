package ru.mephi.kafedra.web.dto

/**
 * @author nivanov
 * on 24.04.17.
 */
class PageDTO {
    Long id, parentId, siteId, rootComponentId
    String title, description, relativePath
    Set<PageDTO> children
}
