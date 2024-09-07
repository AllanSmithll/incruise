package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.NullStudent;
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

    public Student findById(Long id) throws Exception {
        return studentRepository.findById(id).orElse(new NullStudent());
    }

    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void update(Student student) {
        studentRepository.save(student);
    }

    public void remove(Long id) throws Exception {
        studentRepository.deleteById(id);
    }
}

