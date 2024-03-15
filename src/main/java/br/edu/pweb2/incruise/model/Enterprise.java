package br.edu.pweb2.incruise.model;

import java.util.List;

public class Enterprise extends User {

	private String fantasyName;

	private String cnpj;

	private String cellphoneNumber;

	private String personContact;

	private String principalActivity;

	private String urlPage;

	private List<Opportunity> opportunityList;

	/*To Do*/
	//private Document<PDF> comproEnder;


	public Enterprise() {
	}

	public Enterprise(String username, String email, String password, String fantasyName, String cnpj, String cellphoneNumber, String personContact, String principalActivity, String urlPage, List<Opportunity> opportunityList) {
		super(username, email, password);
		this.fantasyName = fantasyName;
		this.cnpj = cnpj;
		this.cellphoneNumber = cellphoneNumber;
		this.personContact = personContact;
		this.principalActivity = principalActivity;
		this.urlPage = urlPage;
		//this.opportunityList = opportunityList;
	}

	public List<Opportunity> listOportunitty() {
		return this.opportunityList;
	}
}
