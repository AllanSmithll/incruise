package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.User;
import br.edu.pweb2.incruise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ModelAndView listUsers() {
        List<User> users = userService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin/users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @PostMapping("/users/toggleStatus/{username}")
    public String toggleUserStatus(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            user.setEnabled(!user.isEnabled());
            userService.save(user);
        }
        return "redirect:/admin/users";
    }
}