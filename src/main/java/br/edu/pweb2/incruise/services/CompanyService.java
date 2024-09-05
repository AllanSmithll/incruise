package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.NullCompany;
import br.edu.pweb2.incruise.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElse(new NullCompany());
    }

    public List<Company> listAll() {
        return companyRepository.findAll();
    }

    public void add(Company company) {
        companyRepository.save(company);
    }

    public void update(Company company) {
        companyRepository.save(company);
    }

    public void remove(Long id) throws Exception {
        this.companyRepository.deleteById(id);
    }
}
