package br.edu.pweb2.incruise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

//@Entity
//@Table(name = "tb_coordinator")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Coordinator extends SchoolMember {

}
