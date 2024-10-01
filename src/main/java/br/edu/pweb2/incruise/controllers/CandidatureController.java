package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.services.CandidatureService;
import br.edu.pweb2.incruise.services.InternshipOfferService;
import br.edu.pweb2.incruise.services.StudentService;

import org.springframework.ui.Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.pweb2.incruise.model.*;;

@Controller
@RequestMapping("/candidature")
public class CandidatureController {

    private final CandidatureService candidatureService;
    private final InternshipOfferService internshipOfferService;
    private final StudentService studentService;

    @Autowired
    public CandidatureController(CandidatureService candidatureService,
            InternshipOfferService internshipOfferService,
            StudentService studentService) {
        this.candidatureService = candidatureService;
        this.internshipOfferService = internshipOfferService;
        this.studentService = studentService;
    }

    @GetMapping("/candidatures")
    public String listCandidatures(Model model) {
        List<Candidature> candidatures = this.candidatureService.findAll();
        model.addAttribute("candidatures", candidatures);
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

    @GetMapping("/apply/{id}")
    public String showApplicationForm(@PathVariable("id") Long offerId, Model model,
            RedirectAttributes redirectAttributes) {
        InternshipOffer offer = internshipOfferService.findById(offerId);
        if (offer == null || offer.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Oferta não encontrada.");
            return "redirect:/internshipOffer/offers";
        }
        model.addAttribute("offer", offer);
        return "offers/application";
    }

    @PostMapping("/apply")
    public String applyForInternship(@RequestParam("offerId") Long offerId,
            @RequestParam("enrollment") String enrollment,
            @RequestParam(value = "message", required = false) String message,
            RedirectAttributes redirectAttributes) {

        Student student = studentService.findByEnrollment(enrollment);
        if (student == null) {
            redirectAttributes.addFlashAttribute("error", "Aluno não encontrado com a matrícula fornecida.");
            return "redirect:/candidature/apply/" + offerId;
        }

        InternshipOffer offer = internshipOfferService.findById(offerId);
        if (offer == null || offer.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Oferta não encontrada.");
            return "redirect:/internshipOffer/offers";
        }

        boolean alreadyApplied = offer.getCandidatureList().stream()
                .anyMatch(candidature -> candidature.getStudent().equals(student));
        if (alreadyApplied) {
            redirectAttributes.addFlashAttribute("error", "Você já se candidatou a esta oferta.");
            return "redirect:/internshipOffer/offers";
        }

        Candidature newCandidature = new Candidature(student, offer, message);
        offer.addCandidature(newCandidature);
        student.addCandidature(newCandidature);

        internshipOfferService.save(offer, offer.getCompanyResponsible());

        redirectAttributes.addFlashAttribute("success", "Candidatura realizada com sucesso.");
        return "redirect:/internshipOffer/offers";
    }
}
