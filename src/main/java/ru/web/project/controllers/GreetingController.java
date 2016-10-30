package ru.web.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author nivanov
 *         on 30.10.16.
 */
@Controller
@RequestMapping("/greeting")
public class GreetingController {

    @RequestMapping(method = RequestMethod.GET)
    public String greeting(@RequestParam(required = false) String name, Model model){
        model.addAttribute("name", name);
        return "greeting";
    }
}
