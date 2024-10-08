package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.Competence;
import br.edu.pweb2.incruise.model.Student;
import br.edu.pweb2.incruise.model.User;
import br.edu.pweb2.incruise.model.exception.DuplicateEmailException;
import br.edu.pweb2.incruise.model.exception.DuplicateEnrollmentException;
import br.edu.pweb2.incruise.model.exception.DuplicateUsernameException;
import br.edu.pweb2.incruise.model.exception.InvalidAgeException;
import br.edu.pweb2.incruise.services.CompetenceService;
import br.edu.pweb2.incruise.services.StudentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final CompetenceService competenceService;
    private final HttpSession session;


    @Autowired
    public StudentController(StudentService studentService, CompetenceService competenceService, HttpSession httpSession) {
        this.studentService = studentService;
        this.competenceService = competenceService;
        this.session = httpSession;
    }

    @GetMapping("/register")
    public String getForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("competences", competenceService.findAll());
        return "students/form";
    }

    @GetMapping("/setSession")
    public String setSession(String username) {
        session.setAttribute("username", username);
        return "index";
    }

    @GetMapping("/students")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("students/list");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_COORDINATOR"))) {

            List<Student> students = studentService.listStudentsByCoordinator(username);
            modelAndView.addObject("students", students);
        } else {
            List<Student> students = studentService.listAll();
            modelAndView.addObject("students", students);
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("student") Student student,
                             BindingResult validation,
                             ModelAndView modelAndView,
                             @RequestParam(value = "competences", required = false) List<Competence> competences,
                             RedirectAttributes attr) {

        if (competences != null) {
            student.setCompetenceList(competences);
        }

        if (validation.hasErrors()) {
            modelAndView.setViewName("students/form");
            modelAndView.addObject("competences", competenceService.findAll());
            return modelAndView;
        }

        try {
            studentService.save(student);
        } catch (DuplicateEnrollmentException e) {
            modelAndView.addObject("enrollmentError", e.getMessage());
            modelAndView.addObject("competences", competenceService.findAll());
            modelAndView.setViewName("students/form");
            return modelAndView;
        } catch (DuplicateUsernameException e) {
            modelAndView.addObject("usernameError", e.getMessage());
            modelAndView.addObject("competences", competenceService.findAll());
            modelAndView.setViewName("students/form");
            return modelAndView;
        } catch (DuplicateEmailException e) {
            modelAndView.addObject("emailError", e.getMessage());
            modelAndView.addObject("competences", competenceService.findAll());
            modelAndView.setViewName("students/form");
            return modelAndView;
        } catch (InvalidAgeException e) {
            modelAndView.addObject("birthdateError", e.getMessage());
            modelAndView.addObject("competences", competenceService.findAll());
            modelAndView.setViewName("students/form");
            return modelAndView;
        }

        String operation = (student.getId() == null) ? "criado" : "salvo";
        attr.addFlashAttribute("mensagem", "Estudante " + student.getName() + " " + operation + " com sucesso!");
        modelAndView.setViewName("redirect:/student/students");
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String disable(@PathVariable("id") Long id) {
        try {
            studentService.disable(id);
            return "redirect:/student/students";
        } catch (Exception e) {
            return "redirect:/student/students";
        }
    }


    @RequestMapping(value = "/info/{username}", method = RequestMethod.GET)
    public String showInfo(@PathVariable("username") String username, Model model) {
        try {

            Student student = this.studentService.findByUserUsername(username);
            model.addAttribute("student", student);
            return "/students/info";

        } catch (Exception e) {
            return "redirect:/not-found";
        }
    }

}