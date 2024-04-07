package br.edu.pweb2.incruise.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Vacancy extends Offer {

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

	public Vacancy(String principalActivity, int workloadSemanal, double transportVoucher, double remunerationValue, List<String> criteriaList) {
		super(principalActivity, workloadSemanal, transportVoucher, remunerationValue, criteriaList);
	}
}
