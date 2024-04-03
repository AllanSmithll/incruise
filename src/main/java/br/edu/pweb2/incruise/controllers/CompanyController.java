package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.Student;
import br.edu.pweb2.incruise.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @RequestMapping("/register")
    public String getForm(Company company, Model model) {
        model.addAttribute("company", company);
        return "companies/form";
    }

    @RequestMapping("/companies")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("companies/list");
        List<Company> companies = companyRepository.list();
        modelAndView.addObject("companies", companies);
        return modelAndView;
    }

    /*@RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(Company company, ModelAndView modelAndView) {
        companyRepository.add(company);
        modelAndView.setViewName("/");
        modelAndView.addObject("companies", companyRepository.list());
        return modelAndView;
    }*/

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(Company company, ModelAndView modelAndView) {
        companyRepository.add(company);
        modelAndView.setViewName("redirect:/company/companies");  // Redirect to companies list
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id) {
        try {
            companyRepository.remove(id);
            return "redirect:/company/companies";
        } catch (Exception e) {
            return "redirect:/company/companies";
        }
    }
}
