package br.edu.pweb2.incruise.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Vacancy extends Opportunity {

	//private List<Student> candidateList;

	private List<Candidature> candidatureList;

	private Integer quanty;
	/**
	 * Retorna uma lista contendo os STUDENTS candidatos a uma vaga
	 * */
	public List<Student> listCandidate() {
		ArrayList<Student> students = new ArrayList<>();

		for (Candidature c : this.candidatureList){
			students.add(c.getStudent());
		}
		return students;
	}

	public Vacancy(Integer id, String principalActivity, int weeklyWorkload, double remunerationValue,
				   double transportVoucher, String prerequisites, List<Candidature> candidatureList) {
		super(id, principalActivity, weeklyWorkload, remunerationValue, transportVoucher, prerequisites);
		this.candidatureList = candidatureList;
	}
}
