package br.edu.pweb2.incruise.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.*;


@Entity
@Table(name = "tb_company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username",  unique = true)
	private User user;

	@Getter
	@Column(unique = true, nullable = false)
	@NotBlank(message = "Nome fantasia é obrigatório.")
	private String fantasyName;

	@Getter
	@Column(unique = true, nullable = false)
	@NotBlank(message = "CNPJ é obrigatório.")
	@Pattern(regexp = "\\d{14}", message = "CNPJ deve ter 14 dígitos.")
	private String cnpj;

	@Column(nullable = false)
	@NotBlank(message = "Número de telefone é obrigatório.")
	@Pattern(regexp = "\\d{10,11}", message = "Telefone deve ter 10 ou 11 dígitos.")
	private String phoneNumber;

	@Column(nullable = false)
	@NotBlank(message = "Contato é obrigatório.")
	private String personContact;

	@Column(nullable = false)
	@NotBlank(message = "Endereço é obrigatório.")
	private String address;

	@Column(nullable = false)
	@NotBlank(message = "Atividade principal é obrigatória.")
	private String principalActivity;

	private String urlPage="";

	@Lob
	@Column(name = "address_proof" )
	@Basic(fetch = FetchType.EAGER)
	private byte[] addressProof;


	@OneToMany(mappedBy = "companyResponsible", orphanRemoval = true, fetch = FetchType.EAGER)
	private List<InternshipOffer> internshipOfferList = new ArrayList<>();

	@Override
	public String toString() {
		return "Company{" +
				"fantasyName='" + fantasyName + '\'' +
				", cnpj='" + cnpj + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", personContact='" + personContact + '\'' +
				", address='" + address + '\'' +
				", principalActivity='" + principalActivity + '\'' +
				", urlPage='" + urlPage + '\'' +
				'}';
	}

	public void  addOpportunity(InternshipOffer internshipOffer) {
		this.internshipOfferList.add(internshipOffer);
	}

	public boolean empty(){
		return false;
	}
}