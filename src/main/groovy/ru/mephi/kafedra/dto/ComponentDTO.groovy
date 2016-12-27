package ru.mephi.kafedra.dto
/**
 * @author nivanov
 * on 26.12.16.
 */
class ComponentDTO {
    Long id, pageId
    String width, height, margin, padding, position, type, text
    Map<String, String> jsEventListeners
    Set<ComponentDTO> children
    String jsAction
    String src
    Integer borderRadius
    Integer fontSize
    String fontFamily, colorHex
    Boolean isHref

}
