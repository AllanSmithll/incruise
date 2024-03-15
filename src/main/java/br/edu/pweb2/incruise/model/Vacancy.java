package br.edu.pweb2.incruise.model;

import br.edu.pweb2.incruise.model.Opportunity;
import br.edu.pweb2.incruise.model.Student;

import java.util.ArrayList;
import java.util.List;

public class Vacancy extends Opportunity {

	//private List<Student> candidateList;

	private List<Candidature> candidatureList;

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


	public Vacancy(){

	}

	public Vacancy(String principalActivity, int workloadSemanal, double transportVoucher, double remunerationValue, List<String> criteriaList) {
		super(principalActivity, workloadSemanal, transportVoucher, remunerationValue, criteriaList);
	}

	public void setCandidatureList(List<Candidature> candidatureList) {
		this.candidatureList = candidatureList;
	}
}
