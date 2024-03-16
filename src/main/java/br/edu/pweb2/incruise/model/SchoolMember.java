package br.edu.pweb2.incruise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolMember extends User {

	private String enrollment;

	private String name;

	private String cpf;

	private Date birthdate;

	private String course;

}
