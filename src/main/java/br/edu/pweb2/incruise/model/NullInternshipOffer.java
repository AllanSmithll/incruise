package br.edu.pweb2.incruise.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NullInternshipOffer extends InternshipOffer {


    @Override
    public Boolean isEmpty(){
        return true;
    }

}
