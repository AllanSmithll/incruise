package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.exception.*;
import br.edu.pweb2.incruise.services.CompanyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final HttpSession session;

    @Autowired
    public CompanyController(CompanyService companyService, HttpSession session) {
        this.companyService = companyService;
        this.session = session;
    }

    @GetMapping("/setSession")
    public String setSession(String username) {
        session.setAttribute("username", username);
        return "home";
    }

    @GetMapping("/register")
    public String getForm(Company company, Model model) {
        model.addAttribute("company", company);
        return "companies/form";
    }

    @GetMapping("/companies")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("companies/list");
        List<Company> companies = companyService.listAll();
        modelAndView.addObject("companies", companies);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(Company company, ModelAndView modelAndView, BindingResult validation) {
        if (validation.hasErrors()) {
            modelAndView.setViewName("companies/form");
            return modelAndView;
        }

        try {
            companyService.save(company);
        } catch (DuplicateUsernameException e) {
            modelAndView.addObject("usernameError", e.getMessage());
            modelAndView.setViewName("companies/form");
            modelAndView.addObject("company", company);
            return modelAndView;
        } catch (DuplicateEmailException e) {
            modelAndView.addObject("emailError", e.getMessage());
            modelAndView.setViewName("companies/form");
            modelAndView.addObject("company", company);
            return modelAndView;
        } catch (InvalidCnpjException | DuplicateCnpjException e) {
            modelAndView.addObject("cnpjError", e.getMessage());
            modelAndView.setViewName("companies/form");
            return modelAndView;
        } catch (DuplicateFantasyNameException e) {
            modelAndView.addObject("fantasyNameError", e.getMessage());
            modelAndView.setViewName("companies/form");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/company/companies");
        return modelAndView;
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        try {
            companyService.remove(id);
            return "redirect:/company/companies";
        } catch (Exception e) {
            return "redirect:/company/companies";
        }
    }

    @GetMapping("/info/{username}")
    public String showInfo(Model model, @PathVariable("username") String username) {
        try {
            Company company = companyService.findByUserUsername(username);
            model.addAttribute("company", company);
            return "companies/info";
        } catch (ItemNotFoundException ie) {
            return "redirect:/not-found";
        }
    }
}