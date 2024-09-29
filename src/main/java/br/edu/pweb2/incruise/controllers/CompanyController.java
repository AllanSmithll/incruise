package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.User;
import br.edu.pweb2.incruise.model.exception.*;
import br.edu.pweb2.incruise.services.CompanyService;
import br.edu.pweb2.incruise.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final HttpSession session;
    private final UserService userService;

    @Autowired
    public CompanyController(CompanyService companyService, HttpSession session, UserService userService) {
        this.companyService = companyService;
        this.session = session;
        this.userService = userService;
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


//    @PostMapping("/save")
//    public ModelAndView save(Company company,
//                             @RequestParam("addressProof") MultipartFile file,
//                             ModelAndView modelAndView,
//                             BindingResult validation) {
//        if (validation.hasErrors()) {
//            modelAndView.setViewName("companies/form");
//            return modelAndView;
//        }
//
//        try {
//            // Verifica se o arquivo não está vazio
//            if (file.isEmpty()) {
//                modelAndView.addObject("fileError", "Por favor, envie um arquivo PDF válido.");
//                modelAndView.setViewName("companies/form");
//                return modelAndView;
//            }
//
//            // Verifica se o tipo de conteúdo é PDF
//            if (!file.getContentType().equals("application/pdf")) {
//                modelAndView.addObject("fileError", "Por favor, envie um arquivo PDF válido.");
//                modelAndView.setViewName("companies/form");
//                return modelAndView;
//            }
//
//            // Converte o arquivo MultipartFile para byte[]
//            company.setAddressProof(file.getBytes());
//
//            // Salva a empresa
//            companyService.save(company);
//
//            // Redireciona para a lista de empresas após salvar com sucesso
//            modelAndView.setViewName("redirect:/company/companies");
//        } catch (IOException e) {
//            modelAndView.addObject("error", "Ocorreu um erro ao processar o arquivo: " + e.getMessage());
//            modelAndView.setViewName("companies/form");
//        } catch (Exception e) {
//            modelAndView.addObject("error", "Ocorreu um erro ao salvar a empresa: " + e.getMessage());
//            modelAndView.setViewName("companies/form");
//        }
//
//        return modelAndView;
//    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        try {
            companyService.remove(id);
            return "redirect:/company/companies";
        } catch (Exception e) {
            return "redirect:/company/companies";
        }
    }

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable("id") Long id, Model model) {
        try {
            System.out.println("Estamos aqui no get!");

            Company currentCompany = companyService.findById(id);

            model.addAttribute("currentCompany", currentCompany);
            return "companies/edit";


        } catch (Exception e) {
            return "redirect:/company/companies";
        }
    }
    @PostMapping("/edit/{id}")
    public String edit
            (
                    @PathVariable("id") Long id,
                    @ModelAttribute("currentCompany") Company companyUpdate) {
        try {
            companyUpdate.setId(id);
            System.out.println(companyUpdate);
//            User userCompany = (User) userService.loadUserByUsername(companyUpdate.getUser().getUsername());

//            companyUpdate.setUser(userCompany);
            companyService.saveAndFlush(companyUpdate);
            System.out.println("Estamos aqui no post!");

            return "redirect:/company/companies";
        } catch (Exception e) {
            System.out.println("Estamos aqui no erro :(!");
            e.printStackTrace();
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