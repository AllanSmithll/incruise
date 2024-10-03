package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.Candidature;
import br.edu.pweb2.incruise.model.InternshipOffer;
import br.edu.pweb2.incruise.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List; 

@Repository
public interface CandidatureRepositoryJpa extends JpaRepository<Candidature, Long> {

    List<Candidature> findByStudent(Student student);

    List<Candidature> findByInternshipOffer(InternshipOffer internshipOffer);

       @Query("SELECT c FROM Candidature c ORDER BY " +
           "CASE WHEN c.status = 'PENDENTE' THEN 1 " +
           "     WHEN c.status = 'REJEITADO' THEN 2 " +
           "     ELSE 3 END")
    List<Candidature> findAllOrderedByStatus();
}
