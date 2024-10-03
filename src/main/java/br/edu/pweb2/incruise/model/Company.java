package br.edu.pweb2.incruise.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
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
	@Column(unique = true)
	private String fantasyName;

	@Getter
	@Column(unique = true)
	private String cnpj;

	@Column(nullable = false)
	private String phoneNumber;

	@Column(nullable = false)
	private String personContact;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
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