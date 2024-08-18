package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/company")
public class CompanyController {

    final
    CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

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

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(Company company, ModelAndView modelAndView) {
        companyRepository.add(company);
        modelAndView.setViewName("companies/list");
        modelAndView.addObject("companies", companyRepository.list());
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

    @GetMapping("/find/{id}")
    public String getCompanyById(@PathVariable Integer id, Model model) {
        // Aqui você pode buscar a empresa com base no ID
        Company companyGet = companyRepository.find(id);

        // Adicione a empresa ao modelo
        model.addAttribute("company", companyGet);

        return "redirect:/companies/info";
    }

}
