package br.edu.pweb2.incruise.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.pweb2.incruise.model.Candidature;
import br.edu.pweb2.incruise.model.InternshipOffer;
import br.edu.pweb2.incruise.services.CandidatureService;
import br.edu.pweb2.incruise.services.InternshipOfferService;

@Service
public class SecurityService {

    @Autowired
    private CandidatureService candidatureService;
    @Autowired
    private InternshipOfferService internshipService;

    public boolean isCandidatureOwner(String username, Long candidatureId) {
        Candidature candidature = candidatureService.findById(candidatureId);
        return candidature != null && candidature.getStudent().getUser().getUsername().equals(username);
    }

    public boolean isCompanyAllowedInCandidature(String username, Long candidatureID) {
        Candidature candidature = candidatureService.findById(candidatureID);
        return candidature != null && candidature.getInternshipOffer().getCompanyResponsible()
                .getUser().getUsername().equals(username);
    }

    public boolean isOwnerOfInternship(String username, Long internShipID){
        InternshipOffer internship = internshipService.findById(internShipID);
        return internship != null && internship.getCompanyResponsible().getUser().getUsername().equals(username);
    }
}