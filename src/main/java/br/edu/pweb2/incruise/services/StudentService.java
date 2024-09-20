package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.NullStudent;
import br.edu.pweb2.incruise.model.Student;
import br.edu.pweb2.incruise.repository.StudentRepositoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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

    @Transactional
    public Student save(Student student) {
        Student existingStudent = studentRepositoryJpa.findByEnrollment(student.getEnrollment());
        if (existingStudent != null && !existingStudent.getId().equals(student.getId())) {
            throw new DataIntegrityViolationException("Esta matrícula já existe.");
        }
        LocalDate birthdate = student.getBirthdate();
        if (birthdate != null) {
            int age = Period.between(birthdate, LocalDate.now()).getYears();
            if (age < 14) {
                throw new IllegalArgumentException("O estudante deve ter no mínimo 14 anos.");
            }
        }
        return studentRepositoryJpa.save(student);
    }

    public void update(Student student) {
        studentRepositoryJpa.save(student);
    }

    public void remove(Long id) throws Exception {
        studentRepositoryJpa.deleteById(id);
    }
}

