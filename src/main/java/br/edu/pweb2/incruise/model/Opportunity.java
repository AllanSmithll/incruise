package br.edu.pweb2.incruise.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Opportunity {

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = false)
	private Company companyResponsible;

	@Column(name = "active", nullable = false)
	private Boolean active = true;

	@ElementCollection(targetClass = Competence.class)
	@CollectionTable(name = "necessary_skills", joinColumns = @JoinColumn(name = "opportunity_id"))
	@Enumerated(EnumType.STRING)
	@Column(name = "necessary_skill")
	private List<Competence> necessarySkills = new ArrayList<>();

	@ElementCollection(targetClass = Competence.class)
	@CollectionTable(name = "desirable_skills", joinColumns = @JoinColumn(name = "opportunity_id"))
	@Enumerated(EnumType.STRING)
	@Column(name = "desirable_skill")
	private List<Competence> desirableSkills = new ArrayList<>();

	public Boolean isEmpty() {
		return false;
	}

	public boolean equals(Long id) {
		return Objects.equals(this.id, id);
	}
}
