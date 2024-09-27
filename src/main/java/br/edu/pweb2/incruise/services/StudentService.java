package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.NullStudent;
import br.edu.pweb2.incruise.model.Student;
import br.edu.pweb2.incruise.model.exception.DuplicateEnrollmentException;
import br.edu.pweb2.incruise.model.exception.InvalidAgeException;
import br.edu.pweb2.incruise.repository.StudentRepositoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepositoryJpa studentRepositoryJpa;
    private final UserService userService;

    @Autowired
    public StudentService(StudentRepositoryJpa studentRepositoryJpa, UserService userService) {
        this.studentRepositoryJpa = studentRepositoryJpa;
        this.userService = userService;
    }

    public Student findByEnrollment(String enrollment) {
        return studentRepositoryJpa.findByEnrollment(enrollment);
    }

    public Student findById(Long id) {
        return studentRepositoryJpa.findById(id).orElse(new NullStudent());
    }

    public Student findByUserUsername(String username) {
        return studentRepositoryJpa.findByUserUsername(username);
    }

    public List<Student> listAll() {
        return studentRepositoryJpa.findAll();
    }

    @Transactional
    public Student save(Student student) throws DuplicateEnrollmentException, InvalidAgeException {
        Student existingStudent = studentRepositoryJpa.findByEnrollment(student.getEnrollment());
        if (existingStudent != null && !existingStudent.getId().equals(student.getId())) {
            throw new DuplicateEnrollmentException("Esta matrícula já existe.");
        }
        LocalDate birthdate = student.getBirthdate();
        if (birthdate != null) {
            int age = Period.between(birthdate, LocalDate.now()).getYears();
            if (age < 14) {
                throw new InvalidAgeException("O estudante deve ter no mínimo 14 anos.");
            }
        }
        if (student.getUser() != null) {
            userService.saveUserWithRole(student.getUser(), "ROLE_STUDENT");
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

