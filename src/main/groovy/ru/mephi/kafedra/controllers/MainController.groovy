package ru.mephi.kafedra.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import ru.mephi.kafedra.dto.UserDTO
import ru.mephi.kafedra.services.UserService

/**
 * @author nivanov
 * on 28.12.16.
 */
@Controller
class MainController {

    @Autowired
    UserService userService

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("userDTO", new UserDTO())
        return "index"
    }

    @PostMapping(value = "/sign_up")
    String signUp(@ModelAttribute UserDTO userDTO) {
        userService.createUser(userDTO)
        return "login"
    }
}
