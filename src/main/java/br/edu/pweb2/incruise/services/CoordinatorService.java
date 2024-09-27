package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Coordinator;
import br.edu.pweb2.incruise.repository.CoordinatorRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordinatorService {
    private final CoordinatorRepositoryJpa coordinatorRepositoryJpa;

    @Autowired
    public CoordinatorService(CoordinatorRepositoryJpa coordinatorRepositoryJpa) {
        this.coordinatorRepositoryJpa = coordinatorRepositoryJpa;
    }

    public Coordinator findByUserUsername(String username) {
        return coordinatorRepositoryJpa.findByUserUsername(username);
    }
}
