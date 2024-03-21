package br.edu.pweb2.incruise.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Entity
//@Table(name = "tb_student")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
public class Student extends SchoolMember {
}
