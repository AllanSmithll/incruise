package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.*;
import br.edu.pweb2.incruise.repository.CompanyRepository;

import br.edu.pweb2.incruise.repository.OpportunityRepository;
import br.edu.pweb2.incruise.repository.StudentRepository;
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

    @Autowired
    OpportunityRepository opportunityRepository;

    StudentRepository studentRepository;

    CompanyRepository companyRepository;

    public InternshipOfferController(OpportunityRepository opportunityRepository,
                                     StudentRepository studentRepository, CompanyRepository companyRepository) {
        this.opportunityRepository = opportunityRepository;
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;

    }

    @RequestMapping("/register")
    public String getForm(InternshipOffer internshipOffer, Model model) {
        model.addAttribute("internshipOffer", internshipOffer);
        List<Company> companies = companyRepository.list();
        model.addAttribute("companies", companies);

        return "/offers/form";
    }

    @RequestMapping("/offers")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("offers/list");
        List<Opportunity> offers = opportunityRepository.list();
        modelAndView.addObject("offers", offers);
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)

    public ModelAndView save(InternshipOffer offer, ModelAndView modelAndView,
                             @RequestParam(value = "necessarySkills", required = false) List<Competence> necessarySkills,
                             @RequestParam(value = "desirableSkills", required = false) List<Competence> derisableSkills
    ) {

        if (necessarySkills != null) {
            offer.setNecessarySkills(necessarySkills);
        }
        if (derisableSkills != null) {
            offer.setDesirableSkills(derisableSkills);
        }
        Company companyCurrent = companyRepository.find(offer.getCompanyResponsible().getId());
        companyCurrent.addOpportunity(offer);
        opportunityRepository.add(offer);
        modelAndView.setViewName("/offers/list");
        modelAndView.addObject("offers", opportunityRepository.list());
        return modelAndView;
    }

    @GetMapping("/apply/{id}")
    public String showApplicationForm(@PathVariable("id") Integer offerId, Model model) throws Exception {
        InternshipOffer offer = (InternshipOffer) opportunityRepository.find(offerId);
        model.addAttribute("offer", offer);
        return "offers/application";
    }

    @PostMapping("/apply")
    public String applyForInternship(@RequestParam("offerId") Integer offerId,

                                     @RequestParam("enrollment") String enrollment,
                                     @RequestParam(value = "message", required = false) String message) throws Exception {
        Student student = studentRepository.findByEnrollment(enrollment);
        InternshipOffer offer = (InternshipOffer) opportunityRepository.find(offerId);

        boolean alreadyApplied = offer.getCandidatureList().stream()
                .anyMatch(candidature -> candidature.getStudent().equals(student));
        if (!alreadyApplied) {
            Candidature newCandidature = new Candidature(student, offer, message);
            offer.addCandidature(newCandidature);
            student.addCandidature(newCandidature);
        } else {
            throw new IllegalStateException("Você já se candidatou a esta oferta.");

        }
        return "redirect:/internshipOffer/offers";
    }

    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.POST)
    public String cancel(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            opportunityRepository.remove(id);
            return "redirect:/internshipOffer/offers";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Não foi possível apagar a oferta.");
            return "redirect:/internshipOffer/offers";
        }
    }

    @GetMapping("/info/{id}")
    public String showInternship(Model model, @PathVariable Integer id) {
        Opportunity opportunity = opportunityRepository.find(id);
        if (opportunity.isEmpty()) {
            return "redirect:/not-found";
        }
        model.addAttribute("opportunity", opportunity);
        String type = (opportunity.getClass() == InternshipOffer.class ? "Oferta de Estágio": "Estágio" );
        model.addAttribute("typeOpportunity", type);

        return "offers/info";}

    public Company findcompanyResponsible(Integer id) {
        Integer idCompany = Integer.valueOf(id);
        return companyRepository.find(idCompany);
    }
}