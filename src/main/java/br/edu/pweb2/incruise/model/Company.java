package br.edu.pweb2.incruise.model;

import java.util.ArrayList;
import java.util.List;

//import jakarta.persistence.*;
import lombok.*;

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

	private String urlPage;

	// A fazer
	private List<Offer> offerList;

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

	public List<Vacancy> getVacancyList() {
		List<Vacancy> vacancies = new ArrayList<>();
		for (Offer offer : offerList) {
			if (offer instanceof Vacancy)
				vacancies.add((Vacancy) offer);
		}
		return vacancies;
	}
}
