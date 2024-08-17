package br.edu.pweb2.incruise.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


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
	private int weeklyWorkload;
	private double remunerationValue = 0.0;
	private double transportVoucher = 0.0;
	private String prerequisites;
	private List<Competence> necessarySkills = new ArrayList<>();
	private List<Competence> desirableSkills = new ArrayList<>();

	public Opportunity(Integer id, String principalActivity, int weeklyWorkload, double remunerationValue,
					   double transportVoucher, String prerequisites) {
		this.id = id;
		this.principalActivity = principalActivity;
		this.weeklyWorkload = weeklyWorkload;
		this.remunerationValue = remunerationValue;
		this.transportVoucher = transportVoucher;
		this.prerequisites = prerequisites;
	}
}
