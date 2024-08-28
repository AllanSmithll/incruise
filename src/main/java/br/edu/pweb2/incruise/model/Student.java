package br.edu.pweb2.incruise.model;

//import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "tb_student")
@Data
@NoArgsConstructor
public class Student extends SchoolMember {
    private LocalDate birthdate;
    private String phoneNumber;
    private List<Competence> competenceList = new ArrayList<>();
    private final List<Candidature> candidatureList = new ArrayList<>();

    public Student(Integer id, String username, String email, String password, String phoneNumber, String enrollment, String name, LocalDate birthdate, String course) {
        super(id, username, email, password, enrollment, name, course);
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
    }

    public void addCandidature(Candidature candidature) {
        candidatureList.add(candidature);
    }

    public void removeCandidature(Candidature candidature) {
        candidatureList.remove(candidature);
    }
}