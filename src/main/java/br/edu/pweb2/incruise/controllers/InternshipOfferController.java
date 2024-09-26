package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.*;
import br.edu.pweb2.incruise.services.CompanyService;
import br.edu.pweb2.incruise.services.CompetenceService;
import br.edu.pweb2.incruise.services.InternshipOfferService;
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

    private final InternshipOfferService internshipOfferService;
    private final StudentService studentService;
    private final CompanyService companyService;
    private final CompetenceService competenceService;

    @Autowired
    public InternshipOfferController(InternshipOfferService internshipOfferService,
                                     StudentService studentService,
                                     CompanyService companyService,
                                     CompetenceService competenceService) {
        this.internshipOfferService = internshipOfferService;
        this.studentService = studentService;
        this.companyService = companyService;
        this.competenceService = competenceService;
    }

    @GetMapping("/register")
    public String getForm(InternshipOffer internshipOffer, Model model) {
        model.addAttribute("internshipOffer", internshipOffer);
        List<Company> companies = companyService.listAll();
        List<Competence> necessarySkills = competenceService.findAll();
        List<Competence> desirableSkills = competenceService.findAll();
        model.addAttribute("companies", companies);
        model.addAttribute("necessarySkills", necessarySkills);
        model.addAttribute("desirableSkills", desirableSkills);
        return "/offers/form";
    }

    @GetMapping("/offers")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("offers/list");
        List<InternshipOffer> offers = internshipOfferService.findAll();
        modelAndView.addObject("offers", offers);
        return modelAndView;
    }

    @GetMapping("/filter")
    public ModelAndView filterOffers(
            @RequestParam(value = "companyName", required = false) String companyName,
            @RequestParam(value = "minRemuneration", required = false) Double minRemuneration,
            @RequestParam(value = "maxRemuneration", required = false) Double maxRemuneration,
            @RequestParam(value = "minWeeklyWorkload", required = false) Integer minWeeklyWorkload,
            @RequestParam(value = "maxWeeklyWorkload", required = false) Integer maxWeeklyWorkload,
            @RequestParam(value = "prerequisites", required = false) String prerequisites,
            ModelAndView modelAndView) {

        List<InternshipOffer> filteredOffers = internshipOfferService.filterOffers(
                companyName, minRemuneration, maxRemuneration, minWeeklyWorkload, maxWeeklyWorkload, prerequisites);

        modelAndView.setViewName("offers/list");
        modelAndView.addObject("offers", filteredOffers);
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("internshipOffer") InternshipOffer offer,
                       @RequestParam("companyId") Long companyId,
                       RedirectAttributes redirectAttributes) {
        Company company = companyService.findById(companyId);
        if (company == null) {
            redirectAttributes.addFlashAttribute("error", "Empresa não encontrada.");
            return "redirect:/internshipOffer/register";
        }
        
        internshipOfferService.save(offer,company);
        
        redirectAttributes.addFlashAttribute("success", "Oferta de estágio salva com sucesso.");
        return "redirect:/internshipOffer/offers";
    }

    @GetMapping("/apply/{id}")
    public String showApplicationForm(@PathVariable("id") Long offerId, Model model, RedirectAttributes redirectAttributes) {
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
            return "redirect:/internshipOffer/apply/" + offerId;
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

    @PostMapping("/cancel/{id}")
    public String cancel(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            internshipOfferService.cancel(id);
            redirectAttributes.addFlashAttribute("success", "Oferta cancelada com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Não foi possível cancelar a oferta.");
        }
        return "redirect:/internshipOffer/offers";
    }

    @GetMapping("/info/{id}")
    public String showInternshipInfo(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        InternshipOffer offer = internshipOfferService.findById(id);
        if (offer == null || offer.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Oferta não encontrada.");
            return "redirect:/internshipOffer/offers";
        }
        List<Competence> necessarySkills = internshipOfferService.findById(id).getNecessarySkills();
        List<Competence> desirableSkills = internshipOfferService.findById(id).getDesirableSkills();
        model.addAttribute("offer", offer);
        model.addAttribute("necessarySkills", necessarySkills);
        model.addAttribute("desirableSkills", desirableSkills);
        return "offers/info";
    }
}
