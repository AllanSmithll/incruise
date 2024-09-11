package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositoryJpa extends JpaRepository<Student, Long> {
    Student findByEnrollment(String enrollment);
}