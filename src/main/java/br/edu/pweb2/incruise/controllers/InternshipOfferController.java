package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.*;
import br.edu.pweb2.incruise.services.CompanyService;
import br.edu.pweb2.incruise.services.OpportunityService;
import br.edu.pweb2.incruise.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/internshipOffer")
public class InternshipOfferController {

    private final OpportunityService opportunityService;
    private final StudentService studentService;
    private final CompanyService companyService;

    @Autowired
    public InternshipOfferController(OpportunityService opportunityService,
                                     StudentService studentService,
                                     CompanyService companyService) {
        this.opportunityService = opportunityService;
        this.studentService = studentService;
        this.companyService = companyService;
    }

    @GetMapping("/register")
    public String getForm(InternshipOffer internshipOffer, Model model) {
        model.addAttribute("internshipOffer", internshipOffer);
        List<Company> companies = companyService.listAll();
        model.addAttribute("companies", companies);
        return "/offers/form";
    }

    @GetMapping("/offers")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("offers/list");
        List<Opportunity> offers = opportunityService.findAll();
        modelAndView.addObject("offers", offers);
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("internshipOffer") InternshipOffer offer,
                       @RequestParam(value = "necessarySkills", required = false) List<Competence> necessarySkills,
                       @RequestParam(value = "desirableSkills", required = false) List<Competence> desirableSkills,
                       @RequestParam("companyId") Integer companyId,
                       RedirectAttributes redirectAttributes) {
        Company company = companyService.findById(companyId);
        if (company == null) {
            redirectAttributes.addFlashAttribute("error", "Empresa não encontrada.");
            return "redirect:/internshipOffer/register";
        }

        opportunityService.add(offer,company);
        
        redirectAttributes.addFlashAttribute("success", "Oferta de estágio salva com sucesso.");
        return "redirect:/internshipOffer/offers";
    }

    @GetMapping("/apply/{id}")
    public String showApplicationForm(@PathVariable("id") Integer offerId, Model model, RedirectAttributes redirectAttributes) {
        InternshipOffer offer = (InternshipOffer) opportunityService.findById(offerId);
        if (offer == null || offer.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Oferta não encontrada.");
            return "redirect:/internshipOffer/offers";
        }
        model.addAttribute("offer", offer);
        return "offers/application";
    }

    @PostMapping("/apply")
    public String applyForInternship(@RequestParam("offerId") Integer offerId,
                                     @RequestParam("enrollment") String enrollment,
                                     @RequestParam(value = "message", required = false) String message,
                                     RedirectAttributes redirectAttributes) throws Exception {

        Student student = studentService.findByEnrollment(enrollment);
        if (student == null) {
            redirectAttributes.addFlashAttribute("error", "Aluno não encontrado.");
            return "redirect:/internshipOffer/offers";
        }

        InternshipOffer offer = (InternshipOffer) opportunityService.findById(offerId);
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

        redirectAttributes.addFlashAttribute("success", "Candidatura realizada com sucesso.");
        return "redirect:/internshipOffer/offers";
    }

    @PostMapping("/cancel/{id}")
    public String cancelOffer(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            opportunityService.remove(id);
            redirectAttributes.addFlashAttribute("success", "Oferta cancelada com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Não foi possível cancelar a oferta.");
        }
        return "redirect:/internshipOffer/offers";
    }

    @GetMapping("/info/{id}")
    public String showInternshipInfo(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Opportunity opportunity = opportunityService.findById(id);
        if (opportunity == null || opportunity.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Oferta não encontrada.");
            return "redirect:/internshipOffer/offers";
        }
        model.addAttribute("opportunity", opportunity);
        String type = (opportunity instanceof InternshipOffer) ? "Oferta de Estágio" : "Estágio";
        model.addAttribute("typeOpportunity", type);
        return "offers/info";
    }
}