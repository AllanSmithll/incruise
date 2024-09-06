package br.edu.pweb2.incruise.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Opportunity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "principal_activity", nullable = false)
	private String principalActivity;

	@Column(name = "weekly_workload", nullable = false)
	private Integer weeklyWorkload = 0;

	@Column(name = "remuneration_value", nullable = false)
	private Double remunerationValue = 0.0;

	@Column(name = "transport_voucher", nullable = false)
	private Double transportVoucher = 0.0;

	@Column(name = "prerequisites")
	private String prerequisites;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", nullable = false)
	private Company companyResponsible;

	@Column(name = "active", nullable = false)
	private Boolean active = true;

	@ManyToMany
	@JoinTable(
			name = "tb_opportunity_competence_skill",
			joinColumns = @JoinColumn(name = "opportunity_id"),
			inverseJoinColumns = @JoinColumn(name = "competence_id")
	)
	private List<Competence> necessarySkills = new ArrayList<>();

	@ManyToMany
	@JoinTable(
			name = "tb_opportunity_competence_skill",
			joinColumns = @JoinColumn(name = "opportunity_id"),
			inverseJoinColumns = @JoinColumn(name = "competence_id")
	)
	private List<Competence> desirableSkills = new ArrayList<>();

	public Boolean isEmpty() {
		return false;
	}

	public boolean equals(Long id) {
		return Objects.equals(this.id, id);
	}
}
