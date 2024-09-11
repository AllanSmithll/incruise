package br.edu.pweb2.incruise.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends SchoolMember {
    private LocalDate birthdate;
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_student_competence_skill",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    private List<Competence> competenceList = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    private final List<Candidature> candidatureList = new ArrayList<>();

    public Student(Long id, String username, String email, String password, String phoneNumber, String enrollment, String name, LocalDate birthdate, String course) {
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