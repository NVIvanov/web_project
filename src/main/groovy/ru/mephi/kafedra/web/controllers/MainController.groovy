package ru.mephi.kafedra.web.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import ru.mephi.kafedra.web.dto.UserDTO

/**
 * @author nivanov
 * on 11.04.17.
 */

@Controller
class MainController {

    @GetMapping("/")
    def index(Model model) {
        model.addAttribute("userDTO", new UserDTO())
        return "index"
    }

    @GetMapping("/login")
    def login() {
        return "login"
    }

    @GetMapping("/auth")
    def signUp() {
        return "auth"
    }
}
