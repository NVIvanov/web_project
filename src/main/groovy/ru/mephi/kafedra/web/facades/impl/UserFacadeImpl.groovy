package ru.mephi.kafedra.web.facades.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.mephi.kafedra.domain.DomainException
import ru.mephi.kafedra.domain.data.entities.SystemUser
import ru.mephi.kafedra.domain.services.UserService
import ru.mephi.kafedra.web.dto.UserDTO
import ru.mephi.kafedra.web.facades.UserFacade
import ru.mephi.kafedra.web.facades.converters.Converter

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 11.04.17.
 */

@Service
class UserFacadeImpl implements UserFacade {

    @Autowired
    UserService userService

    @Autowired
    Converter<UserDTO, SystemUser> userConverter

    @Override
    void createUser(@NotNull UserDTO userDTO) {
        def user = userConverter.convertWithExistModel(null, userDTO)
        user.admin = false
        user.enabled = true
        userService.registerUser(user)
    }

    @Override
    UserDTO updateUser(@NotNull UserDTO userDTO) {
        Optional<SystemUser> userOpt = userService.findUser(userDTO.username)
        if (!userOpt.isPresent())
            throw new DomainException(404, "user.not.found")
        SystemUser user = userOpt.get()
        return userConverter.convert(user)
    }

    @Override
    void deleteUser(@NotNull String username) {
        userService.deleteUser(username)
    }
}
