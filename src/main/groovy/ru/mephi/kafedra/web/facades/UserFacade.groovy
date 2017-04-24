package ru.mephi.kafedra.web.facades

import ru.mephi.kafedra.web.dto.UserDTO

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 11.04.17.
 */
interface UserFacade {
    void createUser(@NotNull UserDTO userDTO)

    UserDTO updateUser(@NotNull UserDTO userVO)

    void deleteUser(@NotNull String username)
}