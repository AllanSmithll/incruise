package br.edu.pweb2.incruise.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NullOpportunity extends Opportunity {

    public NullOpportunity(Integer id, String principalActivity, Integer weeklyWorkload, Double remunerationValue,
            Double transportVoucher, String prerequisites, Company companyResponsable) {
        super(id, principalActivity, weeklyWorkload, remunerationValue, transportVoucher, prerequisites, companyResponsable);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Boolean isEmpty(){
        return true;
    }
    
}
