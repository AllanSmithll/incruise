package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.ItemNotFoundException;
import br.edu.pweb2.incruise.model.Role;
import br.edu.pweb2.incruise.model.User;
import br.edu.pweb2.incruise.services.CompanyService;
import br.edu.pweb2.incruise.services.RoleService;
import br.edu.pweb2.incruise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public ModelAndView save(Company company, ModelAndView modelAndView, BindingResult validation) {
        User user = company.getUser();
        if (user != null) {
            if (validation.hasErrors()) {
                modelAndView.setViewName("companies/form");
                return modelAndView;
            }

            Role role = roleService.findByName("ROLE_COMPANY");
            user.setRole(role);
            try {
                userService.save(user);
                company.setUser(user);
            } catch (DataIntegrityViolationException e) {
                if (e.getMessage().contains("Este nome de usuário já existe")) {
                    modelAndView.addObject("usernameError", "Este nome de usuário já existe.");
                } else if (e.getMessage().contains("email")) {
                    modelAndView.addObject("emailError", "Este email já existe.");
                }
                modelAndView.setViewName("companies/form");
                modelAndView.addObject("company", company);
                return modelAndView;
            }
        }

        try {
            companyService.save(company);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("CNPJ inválido")) {
                modelAndView.addObject("cnpjError", "CNPJ inválido.");
            } else if (e.getMessage().equals("Nome fantasia já existe.")) {
                modelAndView.addObject("fantasyNameError", "Nome fantasia já existe.");
            }
            modelAndView.setViewName("companies/form");
            modelAndView.addObject("company", company);
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