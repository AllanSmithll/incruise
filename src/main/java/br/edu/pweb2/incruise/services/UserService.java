package br.edu.pweb2.incruise.services;

import br.edu.pweb2.incruise.model.Role;
import br.edu.pweb2.incruise.model.User;
import br.edu.pweb2.incruise.model.exception.DuplicateEmailException;
import br.edu.pweb2.incruise.model.exception.DuplicateUsernameException;
import br.edu.pweb2.incruise.repository.UserRepositoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepositoryJpa userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepositoryJpa userRepositoryJpa;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepositoryJpa userRepository, PasswordEncoder passwordEncoder, UserRepositoryJpa userRepositoryJpa, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepositoryJpa = userRepositoryJpa;
        this.roleService = roleService;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return user;
    }

    @Transactional
    public void saveUserWithRole(User user, String roleName) {
        if (userRepositoryJpa.findByUsername(user.getUsername()) != null) {
            throw new DuplicateUsernameException("Este nome de usuário já existe.");
        }
        if (userRepositoryJpa.findByEmail(user.getEmail()) != null) {
            throw new DuplicateEmailException("Este email já existe.");
        }
        Role role = roleService.findByName(roleName);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepositoryJpa.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByUsername(email);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}