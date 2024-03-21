package br.edu.pweb2.incruise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
