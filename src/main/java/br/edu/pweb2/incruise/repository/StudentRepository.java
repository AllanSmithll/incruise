package br.edu.pweb2.incruise.repository;


import br.edu.pweb2.incruise.model.SchoolMember;
import br.edu.pweb2.incruise.model.Student;
//import br.edu.pweb2.incruise.model.User;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class StudentRepository {

    private final Map<Integer, Student> studentMap = new HashMap<>();

    {
        Student s1 = new Student();
        this.add(s1);
        Student s2 = new Student();
        this.add(s2);

    }
    public void add(Student student){

        Integer id = this.generateID();
        student.setId(id);
        studentMap.put(id, student);
    }

    public List<Student> list(){
        return new ArrayList<>(this.studentMap.values());
    }

    public void remove(Integer id){
        this.studentMap.remove(id);
    }

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
    }
}
