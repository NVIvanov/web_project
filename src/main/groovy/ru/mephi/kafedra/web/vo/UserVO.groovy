package ru.mephi.kafedra.web.vo

import com.fasterxml.jackson.annotation.JsonUnwrapped
import ru.mephi.kafedra.web.dto.UserDTO

/**
 * @author nivanov
 * on 11.04.17.
 */

class UserVO {
    @JsonUnwrapped
    UserDTO userDTO
}
