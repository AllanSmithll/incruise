package br.edu.pweb2.incruise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Internship extends Offer {
    Student trainee;

    public Internship(String principalActivity, int workloadSemanal, double transportVoucher, double remunerationValue, List<String> criteriaList) {
        super(principalActivity, workloadSemanal, transportVoucher, remunerationValue, criteriaList);
    }
}
