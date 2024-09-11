package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.InternshipOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipOfferRepositoryJpa extends JpaRepository<InternshipOffer, Long> {
}