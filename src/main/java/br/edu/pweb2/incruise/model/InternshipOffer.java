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
@Table(name = "tb_internship_offer")
public class InternshipOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "principal_activity", nullable = false)
	private String principalActivity;

	@Column(name = "weekly_workload", nullable = false)
	private Integer weeklyWorkload;

	@Column(name = "remuneration_value", nullable = false)
	private Double remunerationValue;

	@Column(name = "transport_voucher", nullable = false)
	private Double transportVoucher;

	@Column(name = "prerequisites")
	private String prerequisites;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE} )
	@JoinColumn(name = "company_id")
	private Company companyResponsible;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private OfferStatus status = OfferStatus.ABERTA;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "tb_internship_offer_necessary_skill",
			joinColumns = @JoinColumn(name = "internship_offer_id"),
			inverseJoinColumns = @JoinColumn(name = "competence_id")
	)
	private List<Competence> necessarySkills = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "tb_internship_offer_desirable_skill",
			joinColumns = @JoinColumn(name = "internship_offer_id"),
			inverseJoinColumns = @JoinColumn(name = "competence_id")
	)
	private List<Competence> desirableSkills = new ArrayList<>();

	@OneToMany(mappedBy = "internshipOffer", cascade = CascadeType.ALL, orphanRemoval = true,
			fetch = FetchType.EAGER)
	private List<Candidature> candidatureList = new ArrayList<>();

	public void addCandidature(Candidature newCandidature) {
		candidatureList.add(newCandidature);
	}

	public Boolean isEmpty() {
		return false;
	}

	public boolean equals(Long id) {
		return Objects.equals(this.id, id);
	}
}
