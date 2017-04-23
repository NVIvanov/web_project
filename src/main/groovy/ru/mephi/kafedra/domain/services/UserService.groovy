package ru.mephi.kafedra.domain.services

import ru.mephi.kafedra.domain.data.entities.SystemUser

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 11.04.17.
 */
interface UserService {
    void registerUser(@NotNull SystemUser user)

    boolean userExists(@NotNull String username)

    Optional<SystemUser> findUser(@NotNull String username)

    void updateUser(@NotNull SystemUser userToUpdate)

    void deleteUser(@NotNull String username)

    boolean permitOperationToCurrentLoggedUser(@NotNull String candidate)

    SystemUser currentLoggedUser()
}