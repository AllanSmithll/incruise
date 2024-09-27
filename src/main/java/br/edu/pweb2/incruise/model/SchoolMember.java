package br.edu.pweb2.incruise.model;

import jakarta.persistence.*;
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
	private String enrollment;

	@Column(name = "name")
	private String name;

	private String course;

	private String phoneNumber;
}