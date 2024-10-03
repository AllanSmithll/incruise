package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.*;
import br.edu.pweb2.incruise.model.exception.DuplicateEnrollmentException;
import br.edu.pweb2.incruise.model.exception.InvalidAgeException;
import br.edu.pweb2.incruise.model.exception.ItemNotFoundException;
import br.edu.pweb2.incruise.repository.StudentRepositoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepositoryJpa studentRepositoryJpa;
    private final UserService userService;
    private final CoordinatorService coordinatorService;


    @Autowired
    public StudentService(StudentRepositoryJpa studentRepositoryJpa, UserService userService, CoordinatorService coordinatorService) {
        this.studentRepositoryJpa = studentRepositoryJpa;
        this.userService = userService;
        this.coordinatorService = coordinatorService;
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

    public List<Student> listStudentsByCoordinator(String coordinatorUsername) {
        Coordinator coordinator = coordinatorService.findByUserUsername(coordinatorUsername);
        return studentRepositoryJpa.findByCourse(coordinator.getCourse());
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

    @Transactional
    public void disable(Long id) {
        Optional<Student> optionalStudent = studentRepositoryJpa.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            User user = student.getUser();
            if (user != null) {
                userService.disableUser(user.getUsername());
            }
            studentRepositoryJpa.save(student);
        } else {
            throw new ItemNotFoundException("Empresa não encontrada.");
        }
    }
}

