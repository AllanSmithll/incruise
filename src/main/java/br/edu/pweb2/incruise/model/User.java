package br.edu.pweb2.incruise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {

	private String username;

	private String email;

	private String password;

	private String phoneNumber;

}
