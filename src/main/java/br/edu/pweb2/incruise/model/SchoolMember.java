package br.edu.pweb2.incruise.model;

import br.edu.pweb2.incruise.model.User;

public class SchoolMember extends User {

	private String matriculation;

	private String name;



	private String course;

	public SchoolMember(String username, String email, String password, String matriculation, String name, String course) {
		super(username, email, password);
		this.matriculation = matriculation;
		this.name = name;
		this.course = course;
	}

	public SchoolMember(){
        super();

    }
}
