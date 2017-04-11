package ru.mephi.kafedra.web.dto

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * @author nivanov
 * on 11.04.17.
 */
class UserDTO {
    @NotNull
    @Size(min = 4, max = 100)
    String username

    @NotNull
    @Size(min = 8, max = 100)
    String password

    @Size(min = 1, max = 200)
    String department

    @NotNull
    @Size(min = 1, max = 200)
    String name
}
