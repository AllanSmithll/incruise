package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.NullCompany;
import br.edu.pweb2.incruise.model.exception.DuplicateCnpjException;
import br.edu.pweb2.incruise.model.exception.DuplicateFantasyNameException;
import br.edu.pweb2.incruise.model.exception.InvalidCnpjException;
import br.edu.pweb2.incruise.repository.CompanyRepositoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepositoryJpa companyRepositoryJpa;
    private final UserService userService;

    @Autowired
    public CompanyService(CompanyRepositoryJpa companyRepositoryJpa, UserService userService) {
        this.companyRepositoryJpa = companyRepositoryJpa;
        this.userService = userService;
    }

    public Company findById(Long id) {
        return companyRepositoryJpa.findById(id).orElse(new NullCompany());
    }

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

    public void update(Company company) {
        companyRepositoryJpa.save(company);
    }

    public void remove(Long id) throws Exception {
        this.companyRepositoryJpa.deleteById(id);
    }
}
