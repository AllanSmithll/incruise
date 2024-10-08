package br.edu.pweb2.incruise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "Data de nascimento é obrigatória.")
    @Past(message = "A data de nascimento deve ser uma data passada.")
    private LocalDate birthdate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_student_competence_skill",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    private List<Competence> competenceList = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE, orphanRemoval = true,
            fetch = FetchType.EAGER)
    private final List<Candidature> candidatureList = new ArrayList<>();

    public void addCandidature(Candidature candidature) {
        candidatureList.add(candidature);
    }

    public void removeCandidature(Candidature candidature) {
        candidatureList.remove(candidature);
    }
}