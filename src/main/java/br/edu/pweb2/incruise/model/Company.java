package br.edu.pweb2.incruise.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tb_company")
@NoArgsConstructor
@AllArgsConstructor
public class Company extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fantasyName;

	private String cnpj;

	private String personContact;

	@OneToOne
	private Address adress;

	private String principalActivity;

	private String urlPage;

	// A fazer
	// private List<Offer> offerList;

	// A FAZER
	// private Document<PDF> comproEnder;
}
