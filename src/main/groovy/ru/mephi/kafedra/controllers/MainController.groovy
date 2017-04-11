package ru.mephi.kafedra.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
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
    String hello(Model model) {
        model.addAttribute("userDTO", new UserDTO())
        return "index"
    }

    @RequestMapping("/login")
    String index() {
        return "login"
    }

    @RequestMapping("/login?error")
    String index(Model model) {
        model.addAttribute("loginError", true)
        return "login"
    }

    @PostMapping(value = "/sign_up")
    String signUp(@ModelAttribute UserDTO userDTO) {
        userService.createUser(userDTO)
        return "login"
    }
}
