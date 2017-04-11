package ru.mephi.kafedra.web.facades.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.mephi.kafedra.domain.data.entities.SystemUser
import ru.mephi.kafedra.domain.services.UserService
import ru.mephi.kafedra.web.dto.UserDTO
import ru.mephi.kafedra.web.facades.UserFacade

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 11.04.17.
 */

@Service
class UserFacadeImpl implements UserFacade {

    @Autowired
    UserService userService

    @Override
    void createUser(@NotNull UserDTO userDTO) {
        SystemUser user = new SystemUser()
        user.username = userDTO.username
        user.password = userDTO.password
        user.name = userDTO.name
        user.department = userDTO.department
        userService.registerUser(user)
    }
}
