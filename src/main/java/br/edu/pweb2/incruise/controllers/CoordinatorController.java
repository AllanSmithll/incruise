package br.edu.pweb2.incruise.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coordination")
public class CoordinatorController {
    private final HttpSession session;

    public CoordinatorController(HttpSession session) {
        this.session = session;
    }

    @GetMapping("/setSession")
    public String setSession(String username) {
        session.setAttribute("username", username);
        return "home";
    }
}
