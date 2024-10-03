package br.edu.pweb2.incruise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Data
@Entity
@Table(name = "tb_coordinator")
@NoArgsConstructor
public class Coordinator extends SchoolMember {

}