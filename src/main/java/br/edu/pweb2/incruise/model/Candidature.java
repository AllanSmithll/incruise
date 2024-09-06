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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "internship_offer_id", nullable = false)
    private InternshipOffer internshipOffer;

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
}
