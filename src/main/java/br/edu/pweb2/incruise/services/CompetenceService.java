package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Competence;
import br.edu.pweb2.incruise.model.NullCompetence;
import br.edu.pweb2.incruise.repository.CompetenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceService {
    private final CompetenceRepository competenceRepository;

    public CompetenceService(CompetenceRepository competenceRepository) {
        this.competenceRepository = competenceRepository;
    }

    public List<Competence> findAll() {
        return competenceRepository.findAll();
    }

    public Competence findById(Long id) {
        return competenceRepository.findById(id).orElse(new NullCompetence());
    }

    @Transactional
    public Competence save(Competence competence) {
        return competenceRepository.save(competence);
    }

    public void deleteById(Long id) {
        competenceRepository.deleteById(id);
    }
}
