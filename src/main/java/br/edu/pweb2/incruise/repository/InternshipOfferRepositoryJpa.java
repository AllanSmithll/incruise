package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.InternshipOffer;
import br.edu.pweb2.incruise.model.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipOfferRepositoryJpa extends JpaRepository<InternshipOffer, Long> {
    List<InternshipOffer> findByStatus(OfferStatus status);
}