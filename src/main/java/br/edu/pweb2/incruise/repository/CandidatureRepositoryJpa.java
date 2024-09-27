package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.Candidature;
import br.edu.pweb2.incruise.model.InternshipOffer;
import br.edu.pweb2.incruise.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatureRepositoryJpa extends JpaRepository<Candidature, Long> {

    Candidature findByStudent(Student student);

    Candidature findByInternshipOffer(InternshipOffer internshipOffer);
}
