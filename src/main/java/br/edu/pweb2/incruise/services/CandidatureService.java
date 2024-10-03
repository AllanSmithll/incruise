package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Candidature;
import br.edu.pweb2.incruise.model.CandidatureStatus;
import br.edu.pweb2.incruise.model.NullCandidature;
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

    public void save(Candidature candidature) {
        candidatureRepositoryJpa.save(candidature);
    }

    private void delete(Long id) throws Exception {
        this.candidatureRepositoryJpa.deleteById(id);
    }
    @Transactional
    public void cancelCandidature(Candidature candidature) throws Exception {
        this.delete(candidature.getId());
    }
    @Transactional
    public void rejectCandidature(Candidature candidature) throws Exception{
        candidature.setStatus(CandidatureStatus.REJEITADA);
        this.delete(candidature.getId());
        this.updateCandidature(candidature);
    }

    public void updateCandidature(Candidature candidature){
        this.candidatureRepositoryJpa.save(candidature);
    }

    public void aproveCandidature(Candidature candidature) throws Exception {
        candidature.setStatus(CandidatureStatus.APROVADA);
        this.delete(candidature.getId());
        this.updateCandidature(candidature);
    }
}