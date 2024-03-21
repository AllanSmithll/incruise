package br.edu.pweb2.incruise.model;

import jakarta.persistence.*;
import lombok.*;

//@Entity
//@Table(name = "tb_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
}
