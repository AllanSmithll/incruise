package br.edu.pweb2.incruise.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
public class Coordinator extends SchoolMember {

    @Override
    public boolean isAdmin(){
        return true;
    }
}