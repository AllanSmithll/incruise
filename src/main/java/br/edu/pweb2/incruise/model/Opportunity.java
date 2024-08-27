package br.edu.pweb2.incruise.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Opportunity {
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	*/
	private Integer id;
	
	private String principalActivity;
	private Integer weeklyWorkload;
	private Double remunerationValue = 0.0;
	private Double transportVoucher = 0.0;
	private String prerequisites;
	private Company companyResponsible;

	private Boolean active = true;
	private List<Competence> necessarySkills = new ArrayList<>();
	private List<Competence> desirableSkills = new ArrayList<>();

	public Opportunity(Integer id, String principalActivity, Integer weeklyWorkload, Double remunerationValue,
					   Double transportVoucher, String prerequisites, Company companyResponsible) {
		this.id = id;
		this.principalActivity = principalActivity;
		this.weeklyWorkload = weeklyWorkload;
		this.remunerationValue = remunerationValue;
		this.transportVoucher = transportVoucher;
		this.prerequisites = prerequisites;
		this.companyResponsible = companyResponsible;
	}

	public Boolean isEmpty(){
		return false;
	}

	public boolean equals(Integer id){
		return Objects.equals(this.id, id);
	}
}
