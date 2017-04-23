package ru.mephi.kafedra.web.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.mephi.kafedra.web.facades.UserFacade
import ru.mephi.kafedra.web.vo.UserVO

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
    void createUser(@RequestBody @Valid UserVO userVO) {
        userFacade.createUser(userVO)
    }

    @PutMapping
    UserVO updateUser(@RequestBody @Valid UserVO userVO) {
        return userFacade.updateUser(userVO)
    }
}
