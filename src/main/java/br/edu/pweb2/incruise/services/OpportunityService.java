package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.InternshipOffer;
import br.edu.pweb2.incruise.model.Opportunity;
import br.edu.pweb2.incruise.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;

    @Autowired
    public OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    public List<Opportunity> findAll() {
        return opportunityRepository.list();
    }

    public Opportunity findById(Integer id) {
        return opportunityRepository.find(id);
    }

    public void add(InternshipOffer offer) {
        opportunityRepository.add(offer);
    }

    public void remove(Integer id) throws Exception {
        opportunityRepository.remove(id);
    }
}