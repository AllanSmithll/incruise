package br.edu.pweb2.incruise.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/choice-register")
    public String showChoiceRegister() {
        return "pages/choice-register";
    }

    @GetMapping("/not-found")
    public String showNotFoundPage() {
        return "/system/not-found";
    }
}
