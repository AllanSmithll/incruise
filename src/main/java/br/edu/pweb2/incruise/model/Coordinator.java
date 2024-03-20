package br.edu.pweb2.incruise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tb_coordinator")
@AllArgsConstructor
public class Coordinator extends SchoolMember {

}
