package br.edu.pweb2.incruise.model;

import java.util.List;

public abstract class Opportunity {

	private String principalActivity;

	private int workloadSemanal;

	private double transportVoucher= 0.0; //vale transporte

	private double remunerationValue= 0.0; //vale transporte

	private List<String> criteriaList; //criterio

	//private List<Competencia> habilidadesDesejadas;

	public Opportunity(String principalActivity, int workloadSemanal, double transportVoucher, double remunerationValue, List<String> criteriaList) {
		this.principalActivity = principalActivity;
		this.workloadSemanal = workloadSemanal;
		this.transportVoucher = transportVoucher;
		this.remunerationValue = remunerationValue;
		this.criteriaList = criteriaList;
	}

	public Opportunity() {
	}




}
