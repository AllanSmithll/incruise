package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEnrollment(String enrollment);
}