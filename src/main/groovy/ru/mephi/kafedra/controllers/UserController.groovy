package ru.mephi.kafedra.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.mephi.kafedra.dto.UserDTO
import ru.mephi.kafedra.services.UserService

import javax.validation.Valid

/**
 * @author nivanov
 * on 27.12.16.
 */
@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    UserService userService

    @PostMapping("/")
    void createUser(@RequestBody @Valid UserDTO userDTO) {
        userService.createUser(userDTO)
    }
}
