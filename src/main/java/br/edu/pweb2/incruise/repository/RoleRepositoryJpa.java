package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryJpa extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
