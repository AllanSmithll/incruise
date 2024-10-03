package br.edu.pweb2.incruise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class SchoolMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username", nullable = false, unique = true)
	private User user;

	@Column(name = "enrollment", unique = true)
	@NotBlank(message = "Matrícula é obrigatória.")
	@Pattern(regexp = "\\d{11}", message = "Matrícula deve ter 11 dígitos.")
	private String enrollment;

	@NotBlank(message = "Nome é obrigatório.")
	@Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
	private String name;

	@NotBlank(message = "Curso é obrigatório.")
	@Size(max = 100, message = "O nome do curso deve ter no máximo 100 caracteres.")
	private String course;

	@NotBlank(message = "Número de telefone é obrigatório.")
	@Pattern(regexp = "\\d{10,11}", message = "Telefone deve ter 10 ou 11 dígitos.")
	private String phoneNumber;
}