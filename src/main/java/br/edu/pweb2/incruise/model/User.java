package br.edu.pweb2.incruise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

	@Id
	@Column(name = "username", nullable = false, unique = true)
	@NotBlank(message = "Nome de usuário é obrigatório.")
	@Size(min = 5, max = 20, message = "Nome de usuário deve ter entre 5 e 20 caracteres.")
	private String username;

	@Column(name = "email", nullable = false, unique = true)
	@NotBlank(message = "Email é obrigatório.")
	@Email(message = "Email deve ser válido.")
	private String email;

	@Column(name = "password", nullable = false)
	@NotBlank(message = "Senha é obrigatória.")
	private String password;

	@Column(name = "enabled", nullable = false)
	private Boolean enabled = true;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(role);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public boolean isAdmin() {
		return role.getAuthority().equals("ROLE_ADMIN");
	}
}