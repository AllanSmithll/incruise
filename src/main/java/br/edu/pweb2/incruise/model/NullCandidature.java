package br.edu.pweb2.incruise.model;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data

public class NullCandidature extends Candidature {

	public NullCandidature() {
		this.setInternshipOffer(new NullInternshipOffer());
		this.setStudent(new NullStudent());
	}

	public boolean empty(){
		return true;
	}
    
}
