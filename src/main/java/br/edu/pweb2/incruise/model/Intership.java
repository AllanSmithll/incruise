package br.edu.pweb2.incruise.model;

import java.util.List;

public class Intership extends Opportunity {
    
    public Intership(String principalActivity, int workloadSemanal, double transportVoucher, double remunerationValue, List<String> criteriaList) {
        super(principalActivity, workloadSemanal, transportVoucher, remunerationValue, criteriaList);
    }

    public Intership() {
    }

    /*
	public Document<PDF> gerarTermoEstagio() {
		return null;
	}*/

}
