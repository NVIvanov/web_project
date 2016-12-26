package ru.mephi.kafedra.dto

import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * @author nivanov
 * on 26.12.16.
 */
class UserDTO {
    String username, name

    @JsonIgnore
    String password
}
