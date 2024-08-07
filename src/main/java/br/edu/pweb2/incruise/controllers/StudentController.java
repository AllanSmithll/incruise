package br.edu.pweb2.incruise.controllers;


import br.edu.pweb2.incruise.model.Competence;
import br.edu.pweb2.incruise.model.Student;
import br.edu.pweb2.incruise.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/register")
    public String getForm(Student student, Model model) {
        model.addAttribute("student", student);
        model.addAttribute("competences", Competence.values());
        return "students/form";

    }

    @RequestMapping("/students")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("students/list");
        List<Student> students = studentRepository.list();
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(Student student, ModelAndView modelAndView, @RequestParam(value = "competences", required = false) List<Competence> competences) {
        if (competences != null) {
            student.setCompetenceList(competences);
        }
        studentRepository.add(student);
        modelAndView.setViewName("students/list");
        modelAndView.setViewName("redirect:/student/students");
        System.out.println(studentRepository.list());
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id) {
        try {
            studentRepository.remove(id);
            return "redirect:/student/students";
        } catch (Exception e) {
            return "redirect:/student/students";
        }
    }
}
