package br.edu.pweb2.incruise.controllers;


import br.edu.pweb2.incruise.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/students")
public class StudentController {

    @RequestMapping("/form")
    public String getForm(Student student, Model model){
        model.addAttribute("student", student);
        return "students/form";

    }

//    @RequestMapping("/save")
//    public String saveStudent(Student student, Model model){
//            model.addAtribute()
//
//
//    }

}
