package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.services.CandidatureService;
import br.edu.pweb2.incruise.services.InternshipOfferService;
import br.edu.pweb2.incruise.services.StudentService;

import org.springframework.ui.Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.pweb2.incruise.model.*;
import org.springframework.web.bind.annotation.RequestBody;;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Student student = this.findStudent(username, redirectAttributes);
        InternshipOffer offer = this.findInternshipOffer(offerId, redirectAttributes);

        if (offer == null || student == null) {
            return "redirect:/internshipOffer/offers";
        }

        model.addAttribute("offer", offer);
        model.addAttribute("student", student);
        return "candidatures/apply";
    }

    @PostMapping("/apply")
    public String applyForInternship(@PathVariable("offerId") Long offerId,
            @RequestParam(value = "message", required = false) String message,
            RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Student student = this.findStudent(username, redirectAttributes);

        if (student == null)
            return "redirect:/candidature/apply/" + offerId;

        InternshipOffer offer = findInternshipOffer(offerId, redirectAttributes);
        if (offer == null)
            return "redirect:/internshipOffer/offers";

        if (hasAlreadyApplied(offer, student, redirectAttributes))
            return "redirect:/internshipOffer/offers";

        createAndSaveCandidature(student, offer, message, redirectAttributes);

        return "redirect:/internshipOffer/offers";
    }

    @PostMapping("/reject/{internshipId}/{candidatureId}")
    public String reject(@PathVariable("candidatureId") Long candidatureId,
            @PathVariable("internshipId") Long internshipId,
            RedirectAttributes redirectAttributes) {
        /**
         * Rejeita a candidatura de um determinado aluno;
         * devendo alterar o status para CandidatureStatus.REJEITADA e atualizar no
         * banco de dados
         */

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Recupera a candidatura
        Candidature candidature = this.findCandidature(candidatureId, redirectAttributes);

        // Caso falhe nas verificações
        if (redirectAttributes.getAttribute("error") != null) {
            return "redirect:/internshipOffer/info/" + internshipId;
        }
        // PAra ver se dá erro
        else if (!candidature.getInternshipOffer().getCompanyResponsible().getUser().getUsername().equals(username)) {
            redirectAttributes.addFlashAttribute("error", "Você não tem permissão para rejeitar esta candidatura.");
        } else {
            // Logica para rejeitar uma candidatura
            try {
                this.candidatureService.rejectCandidature(candidature);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Nãõ foi possível Rejeitar. Confira no log.");
                e.printStackTrace();
            }
            redirectAttributes.addFlashAttribute("success", "Candidatura Rejeitada.");

        }
        return "redirect:/internshipOffer/info/" + internshipId;

    }

    @PostMapping("/cancel/{id}")
    public String cancelStudentCandidature(@PathVariable("id") Long candidatureId,
            RedirectAttributes redirectAttributes) {
        // Obtém o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Recupera a candidatura
        Candidature candidature = candidatureService.findById(candidatureId);

        // Verifica se a candidatura pertence ao estudante autenticado
        if (!candidature.getStudent().getUser().getUsername().equals(username)) {
            redirectAttributes.addFlashAttribute("error", "Você não tem permissão para cancelar esta candidatura.");
            return "redirect:/student/info/" + username;
        }

        // Se não houver erros, remove a candidatura
        try {
            candidatureService.cancelCandidature(candidature);
            redirectAttributes.addFlashAttribute("success", "Candidatura cancelada com sucesso.");
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Não foi possível cancelar. Cheque no Log");
            e.printStackTrace();
        }

        return "redirect:/student/info/" + username;
    }

    @PostMapping("aprove/{id}")
    public String aprove(@PathVariable("id") Long candidatureId, Model model,
            RedirectAttributes redirectAttributes) {
        // Obtém o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Recupera a candidatura
        Candidature candidature = candidatureService.findById(candidatureId);

        // Verifica se a candidatura pertence ao estudante autenticado
        if (!candidature.getInternshipOffer().getCompanyResponsible().getUser().getUsername().equals(username)) {
            redirectAttributes.addFlashAttribute("error", "Você não tem permissão para aprovar esta candidatura.");
            return "redirect:/student/info/" + username;
        }

        // Se não houver erros, aprove a candidatura
        try {
            candidatureService.aproveCandidature(candidature);
            redirectAttributes.addFlashAttribute("success", "Candidatura aprovada com sucesso.");
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Não foi possível Aprovar a Candidatura. Cheque no Log");
            e.printStackTrace();
        }

        return "redirect:/candidature/info/" + candidatureId;
    }

    private Student findStudent(String username, RedirectAttributes redirectAttributes) {
        Student student = studentService.findByUserUsername(username);
        if (student == null) {
            redirectAttributes.addFlashAttribute("error", "Aluno não encontrado.");
        }
        return student;
    }

    private InternshipOffer findInternshipOffer(Long offerId, RedirectAttributes redirectAttributes) {
        // Utilize o método findById(offerId) que retorna um único resultado, sem
        // paginação
        InternshipOffer offer = internshipOfferService.findById(offerId); // Método auxiliar sem paginação
        if (offer == null || offer.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Oferta não encontrada.");
        }
        return offer;
    }

    private Candidature findCandidature(Long candidatureId, RedirectAttributes redirectAttributes) {
        Candidature candidature = candidatureService.findById(candidatureId);
        if (candidature == null || candidature.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Candidatura não encontrada.");
        }
        return candidature;
    }

    private boolean hasAlreadyApplied(InternshipOffer offer, Student student, RedirectAttributes redirectAttributes) {
        boolean alreadyApplied = offer.getCandidatureList().stream()
                .anyMatch(candidature -> candidature.getStudent().equals(student));
        if (alreadyApplied) {
            redirectAttributes.addFlashAttribute("error", "Você já se candidatou a esta oferta.");
        }
        return alreadyApplied;
    }

    private void createAndSaveCandidature(Student student, InternshipOffer offer, String message,
            RedirectAttributes redirectAttributes) {
        Candidature newCandidature = new Candidature(student, offer, message);
        offer.addCandidature(newCandidature);
        student.addCandidature(newCandidature);

        candidatureService.save(newCandidature);
        internshipOfferService.save(offer, offer.getCompanyResponsible());

        redirectAttributes.addFlashAttribute("success", "Candidatura realizada com sucesso.");
    }

}
