package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.InternshipOffer;
import br.edu.pweb2.incruise.model.NullInternshipOffer;
import br.edu.pweb2.incruise.model.OfferStatus;
import br.edu.pweb2.incruise.repository.InternshipOfferRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InternshipOfferService {

    private final InternshipOfferRepositoryJpa internshipOfferRepositoryJpa;

    @Autowired
    public InternshipOfferService(InternshipOfferRepositoryJpa internshipOfferRepositoryJpa) {
        this.internshipOfferRepositoryJpa = internshipOfferRepositoryJpa;
    }

    public List<InternshipOffer> findAll() {
        return internshipOfferRepositoryJpa.findAll();
    }

    public InternshipOffer findById(Long id) {
        return internshipOfferRepositoryJpa.findById(id).orElse(new NullInternshipOffer());
    }

    public List<InternshipOffer> findByCompanyResponsible(Company company) {
        return internshipOfferRepositoryJpa.findByCompanyResponsible(company);
    }

    public List<InternshipOffer> findActiveOffers() {
        return internshipOfferRepositoryJpa.findByStatus(OfferStatus.ABERTA);
    }

    public List<InternshipOffer> filterOffers(String companyName, Double minRemuneration, Double maxRemuneration,
                                          Integer minWeeklyWorkload, Integer maxWeeklyWorkload, String prerequisites) {
        return internshipOfferRepositoryJpa.findAll().stream()
                .filter(offer -> (companyName == null || offer.getCompanyResponsible().getFantasyName().toLowerCase().contains(companyName.toLowerCase())))
                .filter(offer -> (minRemuneration == null || offer.getRemunerationValue() >= minRemuneration))
                .filter(offer -> (maxRemuneration == null || offer.getRemunerationValue() <= maxRemuneration))
                .filter(offer -> (minWeeklyWorkload == null || offer.getWeeklyWorkload() >= minWeeklyWorkload))
                .filter(offer -> (maxWeeklyWorkload == null || offer.getWeeklyWorkload() <= maxWeeklyWorkload))
                .filter(offer -> (prerequisites == null || offer.getPrerequisites().toLowerCase().contains(prerequisites.toLowerCase())))
                .collect(Collectors.toList());
    }

    public List<InternshipOffer> filterOffersByCompanyAndCriteria(Company company,
                                                                  String companyName, Double minRemuneration, Double maxRemuneration,
                                                                  Integer minWeeklyWorkload, Integer maxWeeklyWorkload, String prerequisites) {

        return internshipOfferRepositoryJpa.findByCompanyAndFilters(company, companyName, minRemuneration,
                maxRemuneration, minWeeklyWorkload, maxWeeklyWorkload, prerequisites);
    }


    public void save(InternshipOffer offer, Company company) {
        if(offer.getRemunerationValue() == null){
            offer.setRemunerationValue(0.0);
        }
        if(offer.getCompanyResponsible() == null){
            offer.setCompanyResponsible(company);
        }
        internshipOfferRepositoryJpa.save(offer);
    }

    public void cancel(Long id) throws Exception {
        InternshipOffer offer = findById(id);
        if (offer != null && !offer.isEmpty()) {
            offer.setStatus(OfferStatus.CANCELADA);
            internshipOfferRepositoryJpa.save(offer);
        } else {
            throw new Exception("Oferta não encontrada.");
        }
    }
}