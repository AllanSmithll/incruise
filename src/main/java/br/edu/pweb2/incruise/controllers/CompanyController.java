package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.ItemNotFoundException;
import br.edu.pweb2.incruise.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
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
    public ModelAndView save(Company company, ModelAndView modelAndView) {
        companyService.add(company);
        modelAndView.setViewName("redirect:/company/companies");
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        try {
            companyService.remove(id);
            return "redirect:/company/companies";
        } catch (Exception e) {
            return "redirect:/company/companies";
        }
    }

    @GetMapping("/info/{id}")
    public String showCompanyInfo(Model model, @PathVariable Integer id) {
        try {
            Company company = companyService.findById(id);
            model.addAttribute("company", company);
            return "companies/info";
        } catch (ItemNotFoundException ie) {
            return "redirect:/not-found";
        }
    }
}