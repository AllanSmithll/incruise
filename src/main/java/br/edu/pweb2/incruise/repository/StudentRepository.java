package br.edu.pweb2.incruise.repository;


import br.edu.pweb2.incruise.model.Student;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StudentRepository {

    public static Integer nextId =1;

    public static final List<Student> studentList = new ArrayList<>();

    {
        Student s1 = new Student(0,"bob","bob@gmail","123","(83) 98843-53242","1234","Bob Marley","12345678901", "12/03/2004","Sistemas Para Internet");
        this.add(s1);
        Student s2 = new Student(0,"po","poppey<3Olivia@gmail","123","(83) 98878-53242","1234","Olivia Palito","098765432112", "6/12/1999","Sistemas Para Internet");
        this.add(s2);

    }
    public void add(Student student){
        student.setId( nextId++);
        studentList.add(student);
    }

    public List<Student> list(){
        return studentList;
    }

    public void remove(Integer id) throws Exception {

        Student student =this.find(id);
        studentList.remove(student);

    }


    public Student find(Integer id) throws Exception {

        for(Student s: studentList){
            if(s.getId().equals(id))
                return s;
        }
        throw new Exception("Student not Found");
    }
/*
    private Integer generateID(){

        List<Student> students = this.list();

        Integer id = 1;

        //percore a lista procurando o maior ID, ou uma possível para inserção;
        for(Student student : students){
            //checa se o id gerado é maior que o id Na lista
            if(id >student.getId())
                break; //caso seja, pare de interar
            id++; // se não, aumente o id e continue
        }
        return id;
    }*/
}
