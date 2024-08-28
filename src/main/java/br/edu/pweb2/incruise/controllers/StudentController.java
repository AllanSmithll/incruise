package br.edu.pweb2.incruise.controllers;

import br.edu.pweb2.incruise.model.Candidature;
import br.edu.pweb2.incruise.model.Competence;
import br.edu.pweb2.incruise.model.Student;
import br.edu.pweb2.incruise.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/register")
    public String getForm(Student student, Model model) {
        model.addAttribute("student", student);
        model.addAttribute("competences", Competence.values());
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
        List<Candidature> candidatures = student.getCandidatureList();
        ModelAndView modelAndView = new ModelAndView("students/my-candidatures");
        modelAndView.addObject("candidatures", candidatures);
        modelAndView.addObject("studentName", student.getName());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(Student student, ModelAndView modelAndView, @RequestParam(value = "competences", required = false) List<Competence> competences) {
        if (competences != null) {
            student.setCompetenceList(competences);
        }
        studentService.add(student);
        modelAndView.setViewName("redirect:/student/students");
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        try {
            studentService.remove(id);
            return "redirect:/student/students";
        } catch (Exception e) {
            return "redirect:/student/students";
        }
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public String info(@PathVariable("id") Integer id, Model model) {
        try {

            Student stundent = this.studentService.findById(id);
            model.addAttribute("student", stundent);
            return "/students/info";

        } catch (Exception e) {
            return "redirect:/not-found";
        }
    }
}