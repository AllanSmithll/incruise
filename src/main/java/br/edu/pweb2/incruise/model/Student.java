package br.edu.pweb2.incruise.model;

//import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "tb_student")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Student extends SchoolMember {

    private final List<Candidature> candidatureList = new ArrayList<>();

    public Student(Integer id, String username, String email, String password, String phoneNumber, String enrollment, String name, String cpf, String birthdate, String course) {
        super(id, username, email, password, phoneNumber, enrollment, name, cpf, birthdate, course);
    }

    public void addCandidature(Candidature candidature) {
        candidatureList.add(candidature);
    }
    public void removeCandidature(Candidature candidature) {
        candidatureList.remove(candidature);
    }
}
