package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Company;
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

    public Company findById(Integer id) {
        return companyRepository.find(id);
    }

    public List<Company> listAll() {
        return companyRepository.list();
    }

    public void add(Company company) {
        companyRepository.add(company);
    }

    public void update(Company company) {
        companyRepository.update(company);
    }

    public void remove(Integer id) throws Exception {
        companyRepository.remove(id);
    }
}
