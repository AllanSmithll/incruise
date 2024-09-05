package br.edu.pweb2.incruise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_internship_offer")
public class InternshipOffer extends Opportunity {

	@OneToMany(mappedBy = "internshipOffer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Candidature> candidatureList = new ArrayList<>();

	public void addCandidature(Candidature candidature) {
		candidatureList.add(candidature);
		candidature.setInternshipOffer(this);
	}

	public List<Student> listCandidates() {
		List<Student> students = new ArrayList<>();
		for (Candidature c : candidatureList) {
			students.add(c.getStudent());
		}
		return students;
	}
}
