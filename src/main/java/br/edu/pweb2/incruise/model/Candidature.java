package br.edu.pweb2.incruise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidature {
    private Student student;
    private InternshipOffer offer;
    private String message;
    private LocalDateTime createdDate;

    public Candidature(Student student, InternshipOffer internshipOffer, String message) {
        this.student = student;
        this.offer = internshipOffer;
        this.message = message;
        this.createdDate = LocalDateTime.now();
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.createdDate.format(formatter);
    }
}