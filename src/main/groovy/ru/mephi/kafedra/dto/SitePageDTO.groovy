package ru.mephi.kafedra.dto

/**
 * @author nivanov
 * on 27.12.16.
 */
class SitePageDTO {
    Long id, parent
    String relativePath
    Set<Long> children
}
