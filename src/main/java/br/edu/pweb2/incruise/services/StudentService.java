package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Student;
import br.edu.pweb2.incruise.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student findByEnrollment(String enrollment) throws Exception {
        return studentRepository.findByEnrollment(enrollment);
    }

    public Student findById(Integer id) throws Exception {
        return studentRepository.findBy(id);
    }

    public List<Student> listAll() {
        return studentRepository.list();
    }

    public void add(Student student) {
        studentRepository.add(student);
    }

    public void update(Student student) {
        studentRepository.update(student);
    }

    public void remove(Integer id) throws Exception {
        studentRepository.remove(id);
    }
}

