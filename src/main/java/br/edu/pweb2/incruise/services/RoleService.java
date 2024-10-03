package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Role;
import br.edu.pweb2.incruise.repository.RoleRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepositoryJpa roleRepository;

    public RoleService(RoleRepositoryJpa roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
