package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.ItemNotFoundException;
import br.edu.pweb2.incruise.model.Role;
import br.edu.pweb2.incruise.model.User;
import br.edu.pweb2.incruise.services.CompanyService;
import br.edu.pweb2.incruise.services.RoleService;
import br.edu.pweb2.incruise.services.UserService;
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
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public CompanyController(CompanyService companyService, UserService userService, RoleService roleService) {
        this.companyService = companyService;
        this.userService = userService;
        this.roleService = roleService;
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
        User user = company.getUser();
        if (user != null) {
            Role role = roleService.findByName("ROLE_COMPANY");
            user.setRole(role);
            userService.save(user);
        }
        companyService.save(company);
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

    @GetMapping("/info/{id}")
    public String showCompanyInfo(Model model, @PathVariable Long id) {
        try {
            Company company = companyService.findById(id);
            model.addAttribute("company", company);
            return "companies/info";
        } catch (ItemNotFoundException ie) {
            return "redirect:/not-found";
        }
    }
}