package br.edu.pweb2.incruise.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NullCompany extends Company {
    
	public boolean empty(){
		return true;
	}
}
