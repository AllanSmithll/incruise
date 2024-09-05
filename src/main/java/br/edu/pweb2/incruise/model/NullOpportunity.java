package br.edu.pweb2.incruise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NullOpportunity extends Opportunity {


    @Override
    public Boolean isEmpty(){
        return true;
    }

}
