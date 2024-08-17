package br.edu.pweb2.incruise.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
//import jakarta.persistence.*;


//@Entity
//@Table(name = "tb_company")

@Data
@NoArgsConstructor
public class Company extends User {
	private String fantasyName;

	private String cnpj;

	private String phoneNumber;

	private String personContact;

	private String address;

	private String principalActivity;

	private String urlPage="";

	private List<Opportunity> opportunityList;

	// A FAZER
	// private Document<PDF> comproEnder;

	public Company(Integer id, String username, String email, String password, String fantasyName, String cnpj,
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

	public List<InternshipOffer> getVacancyList() {
		List<InternshipOffer> vacancies = new ArrayList<>();
		for (Opportunity opportunity : opportunityList) {
			
			if (opportunity instanceof InternshipOffer)
				vacancies.add((InternshipOffer) opportunity);
		}
		return vacancies;
	}

	public void  addOpportunity(Opportunity opportunity) {
		this.opportunityList.add(opportunity);
	}
}
