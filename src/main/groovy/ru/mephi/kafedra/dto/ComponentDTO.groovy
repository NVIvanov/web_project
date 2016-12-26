package ru.mephi.kafedra.dto

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference

/**
 * @author nivanov
 * on 26.12.16.
 */
class ComponentDTO {
    String width, height, margin, padding, position, type, page
    Map<String, String> jsEventListeners

    @JsonManagedReference
    Set<ComponentDTO> children

    @JsonBackReference
    ComponentDTO parent
}
