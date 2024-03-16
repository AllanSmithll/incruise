package br.edu.pweb2.incruise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Offer {

	private String principalActivity;

	private int workloadSemanal;

	private double transportVoucher= 0.0; //vale transporte

	private double remunerationValue= 0.0; //

	private List<String> criteriaList; //criterio

	//private List<Competencia> habilidadesDesejadas;
}
