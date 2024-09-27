package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Candidature;
import br.edu.pweb2.incruise.repository.CandidatureRepositoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CandidatureService {
    
    private final CandidatureRepositoryJpa candidatureRepositoryJpa;
    private final UserService userService;

    @Autowired
    public CandidatureService(CandidatureRepositoryJpa candidatureRepositoryJpa, CandidatureRepositoryJpa candidatureRepository, UserService userService) {
        this.candidatureRepositoryJpa = candidatureRepositoryJpa;
        this.userService = userService;
    }

    public Candidature findById(Long id) {
        return candidatureRepositoryJpa.findById(id).orElse(null);
    }

    public List<Candidature> listAll() {
        return candidatureRepositoryJpa.findAll();
    }
    
    public void update(Candidature candidature) {
        candidatureRepositoryJpa.save(candidature);
    }

    public void remove(Long id) throws Exception {
        this.candidatureRepositoryJpa.deleteById(id);
    }
}