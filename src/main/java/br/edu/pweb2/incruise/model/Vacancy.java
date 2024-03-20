package br.edu.pweb2.incruise.model;

import java.util.ArrayList;
import java.util.List;

public class Vacancy extends Offer {

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

	public void setCandidatureList(List<Candidature> candidatureList) {
		this.candidatureList = candidatureList;
	}
}
