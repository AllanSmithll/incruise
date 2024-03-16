package br.edu.pweb2.incruise.model;

import java.util.List;

public class Student extends SchoolMember {

	private List<Candidature> candidatureList;

	public Candidature candidatarOferta(Vacancy vacancy) {
		return null;
	}

	public void cancelarCandidatura(Candidature candidatura) {

	}

	public List<Candidature> listarCandidaturas() {

		return this.candidatureList;
	}

}
