package br.edu.pweb2.incruise.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
*/
	private Integer id;

	private String username;

	private String email;

	private String password;

	private String phoneNumber;

}
