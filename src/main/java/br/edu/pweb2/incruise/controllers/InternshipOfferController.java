package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.*;
import br.edu.pweb2.incruise.services.CompanyService;
import br.edu.pweb2.incruise.services.CompetenceService;
import br.edu.pweb2.incruise.services.InternshipOfferService;
import br.edu.pweb2.incruise.services.StudentService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

import java.util.List;

@Controller
@RequestMapping("/internshipOffer")
public class InternshipOfferController {

    private final InternshipOfferService internshipOfferService;
    private final StudentService studentService;
    private final CompanyService companyService;
    private final CompetenceService competenceService;
    private final HttpSession session;

    @Autowired
    public InternshipOfferController(InternshipOfferService internshipOfferService,
            StudentService studentService,
            CompanyService companyService,
            CompetenceService competenceService,
            HttpSession session) {
        this.internshipOfferService = internshipOfferService;
        this.studentService = studentService;
        this.companyService = companyService;
        this.competenceService = competenceService;
        this.session = session;
    }

    @GetMapping("/register")
    public String getForm(InternshipOffer internshipOffer, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Company company = companyService.findByUserUsername(username);

        model.addAttribute("company", company);

        model.addAttribute("internshipOffer", internshipOffer);
        List<Competence> necessarySkills = competenceService.findAll();
        List<Competence> desirableSkills = competenceService.findAll();
        model.addAttribute("necessarySkills", necessarySkills);
        model.addAttribute("desirableSkills", desirableSkills);

        return "/offers/form";
    }

    @GetMapping("/offers")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("offers/list");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_COMPANY"))) {
            Company company = companyService.findByUserUsername(username);
            List<InternshipOffer> offers = internshipOfferService.findByCompanyResponsible(company);
            modelAndView.addObject("offers", offers);
        } else {
            List<InternshipOffer> offers = internshipOfferService.findAll();
            modelAndView.addObject("offers", offers);
        }

        return modelAndView;
    }

    @GetMapping("/filter")
    public ModelAndView filterOffers(
            @RequestParam(value = "companyName", required = false) String companyName,
            @RequestParam(value = "minRemuneration", required = false) Double minRemuneration,
            @RequestParam(value = "maxRemuneration", required = false) Double maxRemuneration,
            @RequestParam(value = "minWeeklyWorkload", required = false) Integer minWeeklyWorkload,
            @RequestParam(value = "maxWeeklyWorkload", required = false) Integer maxWeeklyWorkload,
            @RequestParam(value = "prerequisites", required = false) String prerequisites,
            ModelAndView modelAndView) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<InternshipOffer> filteredOffers;

        if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_COMPANY"))) {
            Company company = companyService.findByUserUsername(username);

            filteredOffers = internshipOfferService.filterOffersByCompanyAndCriteria(company,
                    companyName, minRemuneration, maxRemuneration, minWeeklyWorkload, maxWeeklyWorkload, prerequisites);
        } else {
            filteredOffers = internshipOfferService.filterOffers(
                    companyName, minRemuneration, maxRemuneration, minWeeklyWorkload, maxWeeklyWorkload, prerequisites);
        }
        modelAndView.setViewName("offers/list");
        modelAndView.addObject("offers", filteredOffers);
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("internshipOffer") InternshipOffer offer,
            @RequestParam("companyId") Long companyId,
            RedirectAttributes redirectAttributes) {
        Company company = companyService.findById(companyId);
        if (company == null) {
            redirectAttributes.addFlashAttribute("error", "Empresa não encontrada.");
            return "redirect:/internshipOffer/register";
        }

        internshipOfferService.save(offer, company);

        redirectAttributes.addFlashAttribute("success", "Oferta de estágio salva com sucesso.");
        return "redirect:/internshipOffer/offers";
    }

    @PostMapping("/cancel/{id}")
    public String cancel(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            internshipOfferService.cancel(id);
            redirectAttributes.addFlashAttribute("success", "Oferta cancelada com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Não foi possível cancelar a oferta.");
        }
        return "redirect:/internshipOffer/offers";
    }

    @GetMapping("/info/{id}")
    public String showInternshipInfo(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes,
            Principal principal) {
        InternshipOffer offer = internshipOfferService.findById(id);
        if (offer == null || offer.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Oferta não encontrada.");
            return "redirect:/internshipOffer/offers";
        }
        
        String username = (String) this.session.getAttribute("username");
        System.out.println("Olha eu aqui"+ username);
        Student student = studentService.findByUserUsername(username);
        List<Competence> necessarySkills = internshipOfferService.findById(id).getNecessarySkills();
        List<Competence> desirableSkills = internshipOfferService.findById(id).getDesirableSkills();
        model.addAttribute("offer", offer);
        model.addAttribute("student", student);
        model.addAttribute("necessarySkills", necessarySkills);
        model.addAttribute("desirableSkills", desirableSkills);


        return "offers/info";
    }
}
