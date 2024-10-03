package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositoryJpa extends JpaRepository<Student, Long> {
    Student findByEnrollment(String enrollment);
    Student findByUserUsername(String username);
    List<Student> findByCourse(String course);
}