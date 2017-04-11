package ru.mephi.kafedra.web.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.mephi.kafedra.web.dto.UserDTO
import ru.mephi.kafedra.web.facades.UserFacade

import javax.validation.Valid

/**
 * @author nivanov
 * on 11.04.17.
 */

@RestController()
@RequestMapping("/users")
class UserController {

    @Autowired
    UserFacade userFacade

    @PostMapping
    void createUser(@RequestBody @Valid UserDTO userDTO) {
        userFacade.createUser(userDTO)
    }
}
