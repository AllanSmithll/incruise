package br.edu.pweb2.incruise.model;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class SchoolMember extends User {

	private String enrollment;

	private String name;

	private String cpf;

	private Date birthdate;

	private String course;

}
