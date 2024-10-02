package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.InternshipOffer;
import br.edu.pweb2.incruise.model.NullCompany;
import br.edu.pweb2.incruise.model.exception.DuplicateCnpjException;
import br.edu.pweb2.incruise.model.exception.DuplicateFantasyNameException;
import br.edu.pweb2.incruise.model.exception.InvalidCnpjException;
import br.edu.pweb2.incruise.model.exception.ItemNotFoundException;
import br.edu.pweb2.incruise.repository.CompanyRepositoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepositoryJpa companyRepositoryJpa;
    private final UserService userService;
    private final InternshipOfferService internshipOfferService;

    @Autowired
    public CompanyService(CompanyRepositoryJpa companyRepositoryJpa, UserService userService, InternshipOfferService internshipOfferService) {
        this.companyRepositoryJpa = companyRepositoryJpa;
        this.userService = userService;
        this.internshipOfferService = internshipOfferService;
    }

    @Transactional
    public Company findById(Long id) {
        return companyRepositoryJpa.findById(id).orElse(new NullCompany());
    }

    @Transactional
    public Company findByUserUsername(String username) {
        return companyRepositoryJpa.findByUserUsername(username);
    }

    @Transactional
    public List<InternshipOffer> findByCompany(Company company) {
        return internshipOfferService.findByCompanyResponsible(company);
    }

    @Transactional
    public List<Company> listAll() {
        return companyRepositoryJpa.findAll();
    }

    @Transactional
    public void save(Company company) throws InvalidCnpjException, DuplicateFantasyNameException {
        if (!UtilService.isValidCNPJ(company.getCnpj())) {
            throw new InvalidCnpjException("CNPJ inválido.");
        }

        if (companyRepositoryJpa.findByCnpj(company.getCnpj()) != null) {
            throw new DuplicateCnpjException("Cnpj já existe.");
        }

        if (companyRepositoryJpa.findByFantasyName(company.getFantasyName()) != null) {
            throw new DuplicateFantasyNameException("Nome fantasia já existe.");
        }

        if (company.getUser() != null) {
            userService.saveUserWithRole(company.getUser(), "ROLE_COMPANY");
        }

        companyRepositoryJpa.save(company);
    }

    @Transactional
    public void saveAndFlush(Company company)
    {
        companyRepositoryJpa.saveAndFlush(company);
    }

    @Transactional
    public void update(Company company) {
        companyRepositoryJpa.save(company);
    }

    public void remove(Long id) throws Exception {
        this.companyRepositoryJpa.deleteById(id);
    }
}
