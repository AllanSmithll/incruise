package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatureRepositoryJpa extends JpaRepository<Candidature, Long> {

    Candidature findByStudent(Student student);

    Candidature findByInternshipOffer(InternshipOffer internshipOffer);
}
