package br.edu.pweb2.incruise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company extends User {

	private String fantasyName;

	private String cnpj;

	private String personContact;



	private String principalActivity;

	private String urlPage;

	private List<Offer> opportunityList;

	// A FAZER
	// private Document<PDF> comproEnder;

	public void addOffer() {

	}

	public List<Offer> listOportunitty() {
		return this.opportunityList;
	}
}
