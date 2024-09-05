package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}