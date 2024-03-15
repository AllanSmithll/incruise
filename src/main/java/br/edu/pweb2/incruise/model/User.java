package br.edu.pweb2.incruise.model;

public abstract class User {

	private String username;

	private String email;

	private String password;

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User() {

	}
}
