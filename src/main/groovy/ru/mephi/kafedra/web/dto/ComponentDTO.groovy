package ru.mephi.kafedra.web.dto

/**
 * @author nivanov
 *         on 25.04.17.
 */
class ComponentDTO {
    Long pageId, id
    String title, type
    Set<ComponentDTO> children
    Map<String, String> attributes
    Map<String, String> jsEventListeners
}
