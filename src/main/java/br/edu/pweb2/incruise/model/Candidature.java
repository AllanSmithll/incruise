package br.edu.pweb2.incruise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_candidature")
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Não cascades operações de remoção para o estudante, já que um estudante pode ter múltiplas candidaturas
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Também não cascades para a oferta de estágio, pois uma oferta pode ter várias candidaturas
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "internship_offer_id", nullable = false)
    private InternshipOffer internshipOffer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CandidatureStatus status = CandidatureStatus.PENDENTE;

    @Column(name = "message")
    private String message;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public Candidature(Student student, InternshipOffer internshipOffer, String message) {
        this.student = student;
        this.internshipOffer = internshipOffer;
        this.message = message;
        this.createdDate = LocalDateTime.now();
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.createdDate.format(formatter);
    }

    public boolean isEmpty() {
        return false;
    }
}
