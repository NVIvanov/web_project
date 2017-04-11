package ru.mephi.kafedra.domain.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import ru.mephi.kafedra.domain.data.entities.SystemUser
import ru.mephi.kafedra.domain.data.repositories.UserRepository
import ru.mephi.kafedra.domain.services.UserService

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 11.04.17.
 */

@Service
class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository

    @Override
    void registerUser(@NotNull SystemUser user) {
        if (userExists(user.username))
            throw new IllegalStateException("User with such username already exists")
        repository.save(user)
    }

    @Override
    boolean userExists(@NotNull String username) {
        return repository.exists(username)
    }

    @Override
    Optional<SystemUser> findUser(@NotNull String username) {
        if (userExists(username))
            return repository.findByUsername(username)
        return Optional.empty()
    }

    @Override
    void updateUser(@NotNull SystemUser userToUpdate) {
        if (!userExists(userToUpdate.username))
            throw new IllegalArgumentException("User with such username is not exist")
        if (!permitOperationToCurrentLoggedUser(userToUpdate.username))
            throw new IllegalStateException("Operation not allowed")
        repository.save(userToUpdate)
    }

    @Override
    void deleteUser(@NotNull String username) {
        if (userExists(username) && permitOperationToCurrentLoggedUser(username))
            repository.delete(username)
    }

    @Override
    boolean permitOperationToCurrentLoggedUser(@NotNull String candidate) {
        String currentUsername = currentLoggedUsername()
        return currentUsername == candidate || currentUsername == "ADMIN"
    }

    private static String currentLoggedUsername() {
        User principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal() as User
        boolean isAdmin = principal.authorities.stream()
                .anyMatch { authority -> authority.authority == "ADMIN" }
        return isAdmin ? "ADMIN" : principal.username
    }
}
