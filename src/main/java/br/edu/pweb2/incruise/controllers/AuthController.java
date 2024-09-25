package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/login")
    public String getLoginForm(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Model model) {
        if (error != null) {
            model.addAttribute("error", "Login ou senha inválidos!");
        }
        if (logout != null) {
            model.addAttribute("message", "Você saiu com sucesso.");
        }
        return "auth/login";
    }
}