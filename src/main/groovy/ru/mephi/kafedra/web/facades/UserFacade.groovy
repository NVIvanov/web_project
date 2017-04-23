package ru.mephi.kafedra.web.facades

import ru.mephi.kafedra.web.vo.UserVO

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 11.04.17.
 */
interface UserFacade {
    void createUser(@NotNull UserVO userDTO)

    UserVO updateUser(@NotNull UserVO userVO)
}