package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepositoryJpa extends JpaRepository<Company, Long> {
    Company findByFantasyName(String fantasyName);
    Company findByCnpj(String cnpj);
    Company findByUserUsername(String username);
}