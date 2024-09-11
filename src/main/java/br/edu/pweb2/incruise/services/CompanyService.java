package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.NullCompany;
import br.edu.pweb2.incruise.repository.CompanyRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepositoryJpa companyRepositoryJpa;

    @Autowired
    public CompanyService(CompanyRepositoryJpa companyRepositoryJpa) {
        this.companyRepositoryJpa = companyRepositoryJpa;
    }

    public Company findById(Long id) {
        return companyRepositoryJpa.findById(id).orElse(new NullCompany());
    }

    public List<Company> listAll() {
        return companyRepositoryJpa.findAll();
    }

    public void add(Company company) {
        companyRepositoryJpa.save(company);
    }

    public void update(Company company) {
        companyRepositoryJpa.save(company);
    }

    public void remove(Long id) throws Exception {
        this.companyRepositoryJpa.deleteById(id);
    }
}
