package br.edu.pweb2.incruise.controllers;


import br.edu.pweb2.incruise.model.Student;
import br.edu.pweb2.incruise.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/register")
    public String getForm(Student student, Model model){
        model.addAttribute("student", student);
        return "students/form";

    }

    @RequestMapping("/students")
    public ModelAndView getAllStudents(ModelAndView model){
        model.setViewName("students/list");
        List<Student> students = studentRepository.list();
        model.addObject("students", students);
        return model;

    }

//    @RequestMapping("/save")
//    public String saveStudent(Student student, Model model){
//            model.addAtribute()
//
//
//    }

}
