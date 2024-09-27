package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatorRepositoryJpa extends JpaRepository<Coordinator, Long> {
    Coordinator findByUserUsername(String username);
}
