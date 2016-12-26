package ru.mephi.kafedra.services

import ru.mephi.kafedra.dto.UserDTO

/**
 * @author nivanov
 * on 26.12.16.
 */
interface UserService {
    void createUser(UserDTO userDTO)
}