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

	private String phoneNumber;

	private String personContact;

	private String address;

	private String principalActivity;

	private String urlPage="";


	@Lob
	@Column(name = "address_proof" )
	private byte[] addressProof;

	@OneToMany(mappedBy = "companyResponsible", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<InternshipOffer> internshipOfferList = new ArrayList<>();

	//private Document<PDF> comproEnder; A fazer

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