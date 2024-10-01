package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.services.CandidatureService;
import org.springframework.ui.Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.pweb2.incruise.model.*;;

@Controller
@RequestMapping("/candidature")
public class CandidatureController {
    
    private final CandidatureService candidatureService;
    
    @Autowired
    public CandidatureController(CandidatureService candidatureService) {
        this.candidatureService = candidatureService;
    }

    @GetMapping("/candidatures")
    public String listCandidatures(Model model){
        List<Candidature> candidatures = this.candidatureService.findAll();
        model.addAttribute("candidatures",candidatures);
        return "/candidatures/list";
    }
    
    @GetMapping("/info/{id}")
    public String showCandidatureInfo(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Candidature candidature = this.candidatureService.findById(id);
        if (candidature == null || candidature.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Oferta não encontrada.");
            return "redirect:/candidature/candidatures";
        }

        model.addAttribute("candidature", candidature);
        return "candidatures/info";
    }
}
