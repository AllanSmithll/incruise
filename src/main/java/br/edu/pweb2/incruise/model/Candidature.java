package br.edu.pweb2.incruise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidature {
    private Student student;
    private InternshipOffer offer;
    private String message;
}
