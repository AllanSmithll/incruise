package br.edu.pweb2.incruise.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Entity
@Table(name = "tb_company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company extends User {
	@Getter
	private String fantasyName;
	
	@Getter
	private String cnpj;

	private String phoneNumber;

	private String personContact;

	private String address;

	private String principalActivity;

	private String urlPage="";

	@OneToMany(mappedBy = "companyResponsible", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<InternshipOffer> internshipOfferList = new ArrayList<>();

	//private Document<PDF> comproEnder; A fazer

	public Company(Long id, String username, String email, String password, String fantasyName, String cnpj,
			String phoneNumber, String personContact, String address, String principalActivity, String urlPage) {
		super(id, username, email, password);
		this.fantasyName = fantasyName;
		this.cnpj = cnpj;
		this.phoneNumber = phoneNumber;
		this.personContact = personContact;
		this.address = address;
		this.principalActivity = principalActivity;
		this.urlPage = urlPage;
	}

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
