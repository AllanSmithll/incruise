package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User, Long> {
    User findByUsername(String username);
}