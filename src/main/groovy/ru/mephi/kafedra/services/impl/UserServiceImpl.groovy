package ru.mephi.kafedra.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.mephi.kafedra.data.entities.SystemUser
import ru.mephi.kafedra.data.repositories.UserRepository
import ru.mephi.kafedra.dto.UserDTO
import ru.mephi.kafedra.services.UserService

/**
 * @author nivanov
 * on 26.12.16.
 */

@Service
class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository

    @Override
    void createUser(UserDTO userDTO) {
        repository.findByUsername(userDTO.username).ifPresent {
            throw new IllegalStateException("User already exists")
        }
        def user = new SystemUser()
        user.name = userDTO.name
        user.password = userDTO.password
        user.username = userDTO.username
        repository.save(user)
    }
}
