package ru.mephi.kafedra.web.facades.converters.impl

import org.springframework.stereotype.Component
import ru.mephi.kafedra.domain.data.entities.SystemUser
import ru.mephi.kafedra.web.dto.UserDTO
import ru.mephi.kafedra.web.facades.converters.Converter

/**
 * @author nivanov
 * on 24.04.17.
 */

@Component
class UserConverter implements Converter<UserDTO, SystemUser> {

    @Override
    SystemUser convertWithExistModel(SystemUser systemUser, UserDTO userDTO) {
        if (systemUser == null)
            systemUser = new SystemUser()
        if (userDTO.email != null)
            systemUser.email = userDTO.email
        if (userDTO.name != null)
            systemUser.name = userDTO.name
        if (userDTO.department != null)
            systemUser.department = userDTO.department
        if (userDTO.password != null)
            systemUser.password = userDTO
        return systemUser
    }

    @Override
    UserDTO convert(SystemUser systemUser) {
        UserDTO dto = new UserDTO()
        dto.department = systemUser.department
        dto.username = systemUser.username
        dto.name = systemUser.name
        dto.email = systemUser.email
        return dto
    }
}
