package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Candidature;
import br.edu.pweb2.incruise.model.NullCandidature;
import br.edu.pweb2.incruise.repository.CandidatureRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatureService {

    private final CandidatureRepositoryJpa candidatureRepositoryJpa;
    private final UserService userService;

    @Autowired
    public CandidatureService(CandidatureRepositoryJpa candidatureRepositoryJpa,
            CandidatureRepositoryJpa candidatureRepository, UserService userService) {
        this.candidatureRepositoryJpa = candidatureRepositoryJpa;
        this.userService = userService;
    }

    public Candidature findById(Long id) {

        return candidatureRepositoryJpa.findById(id).orElse(new NullCandidature());
    }

    public List<Candidature> findAll() {
        return candidatureRepositoryJpa.findAll();
    }

    public void update(Candidature candidature) {
        candidatureRepositoryJpa.save(candidature);
    }

    public void remove(Long id) throws Exception {
        this.candidatureRepositoryJpa.deleteById(id);
    }
}