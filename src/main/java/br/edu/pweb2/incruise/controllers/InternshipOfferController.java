package br.edu.pweb2.incruise.controllers;


import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.Competence;
import br.edu.pweb2.incruise.model.InternshipOffer;
import br.edu.pweb2.incruise.repository.InternshipOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/internshipOffer")
public class InternshipOfferController {

    @Autowired
    InternshipOfferRepository internshipOfferRepository;

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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id) {
        try {
            internshipOfferRepository.remove(id);
            return "redirect:/internshipOffer/offers";
        } catch (Exception e) {
            return "redirect:/internshipOffer/offers";
        }
    }
}