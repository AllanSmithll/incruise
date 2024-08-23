package br.edu.pweb2.incruise.model;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public class InternshipOffer extends Opportunity {

	@Getter
	private List<Candidature> candidatureList = new ArrayList<>();

	public InternshipOffer(Integer id, String principalActivity, Integer weeklyWorkload, Double remunerationValue,
						   Double transportVoucher, String prerequisites, Integer company) {
		super(id, principalActivity, weeklyWorkload, remunerationValue, transportVoucher, prerequisites, company);
	}

	public void addCandidature(Candidature candidature) {
		this.candidatureList.add(candidature);
	}


	public List<Student> listCandidates() {
		List<Student> students = new ArrayList<>();
		for (Candidature c : this.candidatureList) {
			students.add(c.getStudent());
		}
		return students;
	}
}