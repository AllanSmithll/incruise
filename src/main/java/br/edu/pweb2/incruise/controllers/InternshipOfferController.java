package br.edu.pweb2.incruise.controllers;


import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.Competence;
import br.edu.pweb2.incruise.model.InternshipOffer;

import br.edu.pweb2.incruise.model.*;
import br.edu.pweb2.incruise.repository.CompanyRepository;

import br.edu.pweb2.incruise.repository.InternshipOfferRepository;
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
    InternshipOfferRepository internshipOfferRepository;

    StudentRepository studentRepository;
    // CompanyRepository companyRepository;

    public InternshipOfferController(InternshipOfferRepository internshipOfferRepository,
            StudentRepository studentRepository) {
        this.internshipOfferRepository = internshipOfferRepository;
        this.studentRepository = studentRepository;
    }

    public InternshipOfferController(InternshipOfferRepository internshipOfferRepository, StudentRepository studentRepository) {
        this.internshipOfferRepository = internshipOfferRepository;
        this.studentRepository = studentRepository;
    }


    @RequestMapping("/register")
    public String getForm(InternshipOffer internshipOffer, Model model) {
        model.addAttribute("internshipOffer", internshipOffer);
        return "/offers/form";
    }

    @RequestMapping("/offers")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("offers/list");
        List<InternshipOffer> offers = internshipOfferRepository.list();
        modelAndView.addObject("offers", offers);
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)

    public ModelAndView save(InternshipOffer offer, ModelAndView modelAndView, @RequestParam(value = "necessarySkills",
            required = false) List<Competence> necessarySkills, @RequestParam(value = "desirableSkills",
            required = false) List<Competence> derisableSkills) {


   
        if (necessarySkills != null) {
            offer.setNecessarySkills(necessarySkills);
        }
        if (derisableSkills != null) {
            offer.setDesirableSkills(derisableSkills);
        }
        internshipOfferRepository.add(offer);
        modelAndView.setViewName("/offers/list");
        modelAndView.addObject("offers", internshipOfferRepository.list());
        return modelAndView;
    }

    @GetMapping("/apply/{id}")
    public String showApplicationForm(@PathVariable("id") Integer offerId, Model model) throws Exception {
        InternshipOffer offer = internshipOfferRepository.find(offerId);
        model.addAttribute("offer", offer);
        return "offers/application";
    }

    @PostMapping("/apply")
    public String applyForInternship(@RequestParam("offerId") Integer offerId,

            @RequestParam("enrollment") String enrollment,
            @RequestParam(value = "message", required = false) String message) throws Exception {
             @RequestParam("enrollment") String enrollment,
              @RequestParam(value = "message", required = false) String message) throws Exception {

        Student student = studentRepository.findByEnrollment(enrollment);
        InternshipOffer offer = internshipOfferRepository.find(offerId);

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


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id) {
        try {
            internshipOfferRepository.remove(id);
            return "redirect:/internshipOffer/offers";
        } catch (Exception e) {
            return "redirect:/internshipOffer/offers";
        }
    }

    @GetMapping("/info")
    public String showIntership(Model model){
        if (!model.containsAttribute("opportunity")){
            return "redirect:/internshipOffer/offers";
        }
        return "offers/opportunity";
        
    }
    
    @GetMapping("/find/{id}")
    public String findIntership(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        
        Opportunity opportunity = internshipOfferRepository.find(id);

        if (!opportunity.isEmpty()) {
            redirectAttributes.addFlashAttribute("opportunity", opportunity);
            String type = (opportunity.getClass() == Intership.getClass() ? "Estágio":"Oferta");
            redirectAttributes.addFlashAttribute("typeOpportunity", type );
            return "redirect:/internshipOffer/info";

        }else{
            return "redirect:/not-found";
        }

    }
}