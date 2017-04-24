package ru.mephi.kafedra.web.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
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
    def createUser(@RequestBody @Valid UserDTO userVO) {
        userFacade.createUser(userVO)
    }

    @PutMapping
    def updateUser(@RequestBody @Valid UserDTO userVO) {
        return userFacade.updateUser(userVO)
    }

    @DeleteMapping
    def deleteUser(@RequestParam String username) {
        return userFacade.deleteUser(username)
    }
}
