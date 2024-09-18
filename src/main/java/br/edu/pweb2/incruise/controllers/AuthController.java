package br.edu.pweb2.incruise.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        SecurityContextHolder.clearContext();
        redirectAttributes.addFlashAttribute("message", "Você saiu com sucesso.");
        return "redirect:/auth/login?logout=true";
    }

}