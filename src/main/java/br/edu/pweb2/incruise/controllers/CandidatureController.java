package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.Candidature;
import br.edu.pweb2.incruise.services.CandidatureService;

import br.edu.pweb2.incruise.model.exception.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/candidature")
public class CandidatureController{

    private final CandidatureService candidatureService;
    private final HttpSession session;

    @Autowired
    public CandidatureController(CandidatureService candidatureService, HttpSession session) {
        this.candidatureService = candidatureService;
        this.session = session;
    }    

    @GetMapping("/candidatures")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("companies/list");
        List<Candidature> candidatures = candidatureService.listAll();
        modelAndView.addObject("candidatures", candidatures);
        return modelAndView;
    }

    @GetMapping("/info/{id}")
    public String showCompanyInfo(Model model, @PathVariable Long id) {
        try {
            Candidature candidature = candidatureService.findById(id);
            model.addAttribute("candidature", candidature);
            return "candidatures/info";
        } catch (ItemNotFoundException ie) {
            return "redirect:/not-found";
        }
    }

}
