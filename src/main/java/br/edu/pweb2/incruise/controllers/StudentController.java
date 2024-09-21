package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.*;
import br.edu.pweb2.incruise.model.exception.DuplicateEnrollmentException;
import br.edu.pweb2.incruise.model.exception.InvalidAgeException;
import br.edu.pweb2.incruise.services.CompetenceService;
import br.edu.pweb2.incruise.services.RoleService;
import br.edu.pweb2.incruise.services.StudentService;
import br.edu.pweb2.incruise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final CompetenceService competenceService;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public StudentController(StudentService studentService, CompetenceService competenceService, UserService userService, RoleService roleService) {
        this.studentService = studentService;
        this.competenceService = competenceService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public String getForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("competences", competenceService.findAll());
        return "students/form";
    }

    @GetMapping("/students")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("students/list");
        List<Student> students = studentService.listAll();
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/my-candidatures")
    public ModelAndView getMyCandidatures() throws Exception {
        String enrollment = "1234";
        Student student = studentService.findByEnrollment(enrollment);
        if (student == null) {
            throw new Exception("Student not found.");
        }
//        List<Candidature> candidatures = student.getCandidatureList();
        ModelAndView modelAndView = new ModelAndView("students/my-candidatures");
//        modelAndView.addObject("candidatures", candidatures);
        modelAndView.addObject("studentName", student.getName());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(Student student, ModelAndView modelAndView, BindingResult validation,
                             @RequestParam(value = "competences", required = false) List<Competence> competences,
                             RedirectAttributes attr) {
        if (competences != null) {
            student.setCompetenceList(competences);
        }

        User user = student.getUser();
        if (user != null) {
            if (validation.hasErrors()) {
                modelAndView.setViewName("students/form");
                return modelAndView;
            }

            Role role = roleService.findByName("ROLE_STUDENT");
            user.setRole(role);

            try {
                userService.save(user);
            } catch (DataIntegrityViolationException e) {
                if (e.getMessage().contains("Este nome de usuário já existe")) {
                    modelAndView.addObject("usernameError", "Este nome de usuário já existe.");
                } else if (e.getMessage().contains("email")) {
                    modelAndView.addObject("emailError", "Este email já existe.");
                }
                modelAndView.setViewName("students/form");
                modelAndView.addObject("student", student);
                modelAndView.addObject("competences", competenceService.findAll());
                return modelAndView;
            }
        }

        try {
            studentService.save(student);
        } catch (DuplicateEnrollmentException e) {
            modelAndView.addObject("enrollmentError", e.getMessage());
            modelAndView.setViewName("students/form");
            return modelAndView;
        } catch (InvalidAgeException e) {
            modelAndView.addObject("birthdateError", e.getMessage());
            modelAndView.setViewName("students/form");
            return modelAndView;
        }

        String operation = (student.getId() == null) ? "criado" : "salvo";
        attr.addFlashAttribute("mensagem", "Estudante " + student.getName() + " " + operation + " com sucesso!");

        modelAndView.setViewName("redirect:/student/students");
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        try {
            studentService.remove(id);
            return "redirect:/student/students";
        } catch (Exception e) {
            return "redirect:/student/students";
        }
    }


    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public String info(@PathVariable("id") Long id, Model model) {
        try {

            Student stundent = this.studentService.findById(id);
            model.addAttribute("student", stundent);
            return "/students/info";

        } catch (Exception e) {
            return "redirect:/not-found";
        }
    }

}