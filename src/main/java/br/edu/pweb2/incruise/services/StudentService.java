package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.NullStudent;
import br.edu.pweb2.incruise.model.Student;
import br.edu.pweb2.incruise.repository.StudentRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepositoryJpa studentRepositoryJpa;

    @Autowired
    public StudentService(StudentRepositoryJpa studentRepositoryJpa) {
        this.studentRepositoryJpa = studentRepositoryJpa;
    }

    public Student findByEnrollment(String enrollment) throws Exception {
        return studentRepositoryJpa.findByEnrollment(enrollment);
    }

    public Student findById(Long id) throws Exception {
        return studentRepositoryJpa.findById(id).orElse(new NullStudent());
    }

    public List<Student> listAll() {
        return studentRepositoryJpa.findAll();
    }

    public void save(Student student) {
        studentRepositoryJpa.save(student);
    }

    public void update(Student student) {
        studentRepositoryJpa.save(student);
    }

    public void remove(Long id) throws Exception {
        studentRepositoryJpa.deleteById(id);
    }
}

