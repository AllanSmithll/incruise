package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.InternshipOffer;
import br.edu.pweb2.incruise.model.NullOpportunity;
import br.edu.pweb2.incruise.model.Opportunity;
import br.edu.pweb2.incruise.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;

    @Autowired
    public OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    public List<Opportunity> findAll() {
        return opportunityRepository.findAll();
    }

    public Opportunity findById(Long id) {
        return opportunityRepository.findById(id).orElse(new NullOpportunity());
    }

    public List<Opportunity> filterOffers(String companyName, Double minRemuneration, Double maxRemuneration,
                                          Integer minWeeklyWorkload, Integer maxWeeklyWorkload, String prerequisites) {
        return opportunityRepository.findAll().stream()
                .filter(offer -> (companyName == null || offer.getCompanyResponsible().getFantasyName().toLowerCase().contains(companyName.toLowerCase())))
                .filter(offer -> (minRemuneration == null || offer.getRemunerationValue() >= minRemuneration))
                .filter(offer -> (maxRemuneration == null || offer.getRemunerationValue() <= maxRemuneration))
                .filter(offer -> (minWeeklyWorkload == null || offer.getWeeklyWorkload() >= minWeeklyWorkload))
                .filter(offer -> (maxWeeklyWorkload == null || offer.getWeeklyWorkload() <= maxWeeklyWorkload))
                .filter(offer -> (prerequisites == null || offer.getPrerequisites().toLowerCase().contains(prerequisites.toLowerCase())))
                .collect(Collectors.toList());
    }

    public void add(Opportunity offer, Company company) {
        if(offer.getRemunerationValue() == null){
            offer.setRemunerationValue(0.0);
        }
        offer.setCompanyResponsible(company);

//        company.addOpportunity(offer);
        opportunityRepository.save(offer);
    }

    public void remove(Long id) throws Exception {
        opportunityRepository.deleteById(id);
    }
}