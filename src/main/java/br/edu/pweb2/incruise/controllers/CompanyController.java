package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.InternshipOffer;
import br.edu.pweb2.incruise.model.User;
import br.edu.pweb2.incruise.model.exception.*;
import br.edu.pweb2.incruise.services.CompanyService;
import br.edu.pweb2.incruise.services.InternshipOfferService;
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
    private final InternshipOfferService internshipOfferService;

            ;
    @Autowired
    public CompanyController(CompanyService companyService, HttpSession session, UserService userService, InternshipOfferService internshipOfferService) {
        this.companyService = companyService;
        this.session = session;
        this.userService = userService;
        this.internshipOfferService = internshipOfferService;
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

          InternshipOffer offer = (InternshipOffer) internshipOfferService.findByCompanyResponsible(companyService.findById(id));
          offer.setCompanyResponsible(null);
            return "redirect:/company/companies";
        } catch (Exception e) {
            return "redirect:/company/companies";
        }
    }

    @GetMapping("/edit/{id}/{username}")
    public String getEditForm(@PathVariable("id") Long id,@PathVariable("username") String username, Model model) {
        try {

            System.out.println("Estamos aqui no get!");

            Company currentCompany = companyService.findById(id);
            System.out.println("Username for company user: " + currentCompany.getUser().getUsername());
            User user = (User) userService.loadUserByUsername(username);

            Company company = companyService.findByUserUsername(username);
            System.out.println("Encontrada pelo user: " + company);

            System.out.println("User direto pelo id: " + user.getUsername());


            model.addAttribute("currentCompany", currentCompany);
            model.addAttribute("currentUser",user);

            return "companies/edit";


        } catch (Exception e) {
            System.out.println("n pode entrar aqui " );
            e.printStackTrace();

            return "redirect:/company/companies";
        }
    }
    @PostMapping("/edit/{id}")
    public String edit
            (@PathVariable("id") Long id,
             @ModelAttribute("currentCompany") Company companyUpdate
            )  {
        try {
            User user1 = companyUpdate.getUser();
            if (user1 != null) {
                System.out.println("Username for user in post method in if: " + user1.getUsername() + user1.toString());
            } else {
                System.out.println("User is null!");
            }

            // Recuperar o username do user associado ao companyUpdate
            assert user1 != null;
            String username = user1.getUsername();
            System.out.println("Username duplicado? " + username);

            System.out.println("Buscar user " +  user1.getUsername());
            // Buscar o user pelo username (ou ID, dependendo da sua lógica de negócio)
            User userProcurado = (User) userService.findByUsername(user1.getUsername());
            System.out.println("Username duplicado? " + userProcurado.getUsername());

            // Associar o user existente ao companyUpdate
            companyUpdate.setUser(user1);


            companyService.saveAndFlush(companyUpdate);

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