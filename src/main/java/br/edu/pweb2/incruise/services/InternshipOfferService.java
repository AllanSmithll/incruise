package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.InternshipOffer;
import br.edu.pweb2.incruise.model.NullInternshipOffer;
import br.edu.pweb2.incruise.model.OfferStatus;
import br.edu.pweb2.incruise.repository.InternshipOfferRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<InternshipOffer> findAll(Pageable pageable) {
        return internshipOfferRepositoryJpa.findInternshipOffers(pageable);
    }

    public Page<InternshipOffer> findById(Long id, Pageable pageable) {
        return internshipOfferRepositoryJpa.findInternshipOfferById(id, pageable);
    }

    public InternshipOffer findById(Long id) {
        Page<InternshipOffer> offersPage = internshipOfferRepositoryJpa.findInternshipOfferById(id, Pageable.unpaged());
        return offersPage.isEmpty() ? new NullInternshipOffer() : offersPage.getContent().get(0);
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
        Page<InternshipOffer> offersPage = findById(id, Pageable.unpaged());

        if (!offersPage.isEmpty()) {
            InternshipOffer offer = offersPage.getContent().getFirst();
            offer.setStatus(OfferStatus.CANCELADA);
            internshipOfferRepositoryJpa.save(offer);
        } else {
            throw new Exception("Oferta não encontrada.");
        }
    }

}