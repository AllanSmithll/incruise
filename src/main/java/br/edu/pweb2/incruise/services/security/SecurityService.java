package br.edu.pweb2.incruise.services;
import br.edu.pweb2.incruise.services.CandidatureService;
import br.edu.pweb2.incruise.services.InternshipService;

import br.edu.pweb2.incruise.model.Candidature;
import br.edu.pweb2.incruise.model.InternshipOffer;


@Service
public class SecurityService {

    @Autowired
    private CandidatureService candidatureService;
    private InternshipService internshipService;

    public boolean isCandidatureOwner(String username, Long candidatureId) {
        Candidature candidature = candidatureService.findById(candidatureId);
        return candidature != null && candidature.getStudent().getUser().getUsername().equals(username);
    }

    public boolean isCompanyAllowedInCandidature(String username, Long candidatureID){
        Candidature candidature = candidatureService.findById(candidatureId);
        return candidature != null && candidature.getInternshipOffer().getCompanyResponsible().getUsername().equals(username)
    }

    public boolean isOwnerOfInternship(String username, Long internShipID){
        InternShip internship = internshipService.findById(internshipId);
        return internship != null && internship.getCompanyResponsible().getUsername().equals(username)
    }
}