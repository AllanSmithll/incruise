package br.edu.pweb2.incruise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Entity
//@Table(name = "tb_coordinator")
@Data
@NoArgsConstructor
public class Coordinator extends SchoolMember {

    public Coordinator(Integer id, String username, String email, String password, String phoneNumber, String enrollment, String name, String cpf, String birthdate, String course) {
        super(id, username, email, password, phoneNumber, enrollment, name, cpf, birthdate, course);
    }
}
