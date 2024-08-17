package br.edu.pweb2.incruise.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Internship extends Opportunity {
    public Internship(Integer id, String principalActivity, int weeklyWorkload, double remunerationValue,
                      double transportVoucher, String prerequisites) {
        super(id, principalActivity, weeklyWorkload, remunerationValue, transportVoucher, prerequisites);
    }
}
