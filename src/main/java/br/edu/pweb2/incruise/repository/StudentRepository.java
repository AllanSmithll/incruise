package br.edu.pweb2.incruise.repository;


import br.edu.pweb2.incruise.model.Student;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class StudentRepository {

    public static Long nextId = 1L;

    public static final List<Student> studentList = new ArrayList<>();

    {
        Student s1 = new Student(0L, "bob", "bob@gmail", "123", "(83) 98843-53242", "1234", "Bob Marley", LocalDate.of(2004, 11, 4), "Sistemas Para Internet");
        this.add(s1);
        Student s2 = new Student(0L, "po", "poppey<3Olivia@gmail", "123", "(83) 98878-53242", "1234", "Olivia Palito", LocalDate.of(2000, 8, 28), "Sistemas Para Internet");
        this.add(s2);

    }

    public void add(Student student) {
        student.setId(nextId++);
        studentList.add(student);
    }

    public List<Student> list() {
        return studentList;
    }

    public void remove(Integer id) throws Exception {

        Student student = this.findBy(id);
        studentList.remove(student);

    }

    public Student findBy(Integer id) {
        return studentList.get(id);
    }

    public Student findByEnrollment(String enrollment) throws Exception {
        for (Student s : studentList) {
            if (s.getEnrollment().equals(enrollment))
                return s;
        }
        throw new Exception("Student not Found");
    }

    public void update(Student student) {
    }
}