package br.edu.pweb2.incruise.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/choice-register")
    public String showChoiceRegister() {
        return "pages/choice-register";
    }
}
