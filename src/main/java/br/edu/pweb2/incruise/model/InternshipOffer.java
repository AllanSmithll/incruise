package br.edu.pweb2.incruise.model;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class InternshipOffer extends Opportunity {

	private List<Candidature> candidatureList;

	public InternshipOffer(Integer id, String principalActivity, Integer weeklyWorkload, Double remunerationValue,
			Double transportVoucher, String prerequisites, Company responsable) {

		super(id, principalActivity, weeklyWorkload, remunerationValue, transportVoucher, prerequisites, responsable);
		
		//this.candidatureList = candidatureList;
	}

	/**
	 * Retorna uma lista contendo os STUDENTS candidatos a uma vaga
	 */
	public List<Student> listCandidate() {
		ArrayList<Student> students = new ArrayList<>();

		for (Candidature c : this.candidatureList) {
			students.add(c.getStudent());
		}
		return students;
	}
}
