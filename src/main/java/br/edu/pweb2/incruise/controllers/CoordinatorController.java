package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.Coordinator;
import br.edu.pweb2.incruise.model.exception.ItemNotFoundException;
import br.edu.pweb2.incruise.services.CoordinatorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coordinator")
public class CoordinatorController {
    private final HttpSession session;
    private final CoordinatorService coordinatorService;

    public CoordinatorController(HttpSession session, CoordinatorService coordinatorService) {
        this.session = session;
        this.coordinatorService = coordinatorService;
    }

    @GetMapping("/setSession")
    public String setSession(String username) {
        session.setAttribute("username", username);
        return "home";
    }

    @GetMapping("/info/{username}")
    public String showInfo(Model model, @PathVariable("username") String username) {
        try {
            Coordinator coordinator = coordinatorService.findByUserUsername(username);
            model.addAttribute("coordinator", coordinator);
            return "coordinators/info";
        } catch (ItemNotFoundException ie) {
            return "redirect:/not-found";
        }
    }
}
