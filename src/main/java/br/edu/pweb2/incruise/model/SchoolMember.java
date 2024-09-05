package br.edu.pweb2.incruise.model;

//import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.*;

//import java.util.Date;

@Data
@Table(name = "tb_school_member")
@NoArgsConstructor
@AllArgsConstructor
public abstract class SchoolMember extends User {

	private String enrollment;

	private String name;

	private String course;

	public SchoolMember(Long id, String username, String email, String password, String enrollment, String name, String course) {
		super(id, username, email, password);
		this.enrollment = enrollment;
		this.name = name;
		this.course = course;
	}
}