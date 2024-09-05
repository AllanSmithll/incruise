package br.edu.pweb2.incruise.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NullStudent extends Student {

    public boolean empty(){
        return true;
    }
}
