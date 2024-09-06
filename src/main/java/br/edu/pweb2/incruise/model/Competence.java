package br.edu.pweb2.incruise.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_competence")
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public boolean isEmpty(){
        return false;
    }
}