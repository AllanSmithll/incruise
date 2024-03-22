package br.edu.pweb2.incruise.model;

//import jakarta.persistence.MappedSuperclass;
import lombok.*;

//import java.util.Date;

@Data
@NoArgsConstructor
public abstract class SchoolMember extends User {

	private String enrollment;

	private String name;

	private String cpf;

	private String birthdate;

	private String course;

	public SchoolMember(Integer id, String username, String email, String password, String phoneNumber, String enrollment, String name, String cpf, String birthdate, String course) {
		super(id, username, email, password, phoneNumber);
		this.enrollment = enrollment;
		this.name = name;
		this.cpf = cpf;
		this.birthdate = birthdate;
		this.course = course;
	}
}
