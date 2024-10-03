package br.edu.pweb2.incruise.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NullCompetence extends Competence {

    @Override
    public boolean isEmpty(){
        return true;
    }
}
