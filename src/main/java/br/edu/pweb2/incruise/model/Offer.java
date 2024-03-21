package br.edu.pweb2.incruise.model;

import lombok.*;

import java.util.List;


@Data
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
