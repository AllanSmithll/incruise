package br.edu.pweb2.incruise.repository;

import br.edu.pweb2.incruise.model.Company;
import br.edu.pweb2.incruise.model.InternshipOffer;
import br.edu.pweb2.incruise.model.OfferStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipOfferRepositoryJpa extends JpaRepository<InternshipOffer, Long> {
    @Query("SELECT offer FROM InternshipOffer offer WHERE offer.id = :id")
    Page<InternshipOffer> findInternshipOfferById(@Param("id") Long id, Pageable pageable);

    @Query("SELECT offer FROM InternshipOffer offer")
    Page<InternshipOffer> findInternshipOffers(Pageable pageable);

    List<InternshipOffer> findByStatus(OfferStatus status);

    Page<InternshipOffer> findByCompanyResponsible(Company company, Pageable pageable);
    List<InternshipOffer> findByCompanyResponsible(Company company);

    @Query("SELECT offer FROM InternshipOffer offer WHERE offer.companyResponsible = :company AND "
            + "(:fantasyName IS NULL OR offer.companyResponsible.fantasyName LIKE %:fantasyName%) AND "
            + "(:minRemuneration IS NULL OR offer.remunerationValue >= :minRemuneration) AND "
            + "(:maxRemuneration IS NULL OR offer.remunerationValue <= :maxRemuneration) AND "
            + "(:minWeeklyWorkload IS NULL OR offer.weeklyWorkload >= :minWeeklyWorkload) AND "
            + "(:maxWeeklyWorkload IS NULL OR offer.weeklyWorkload <= :maxWeeklyWorkload) AND "
            + "(:prerequisites IS NULL OR offer.prerequisites LIKE %:prerequisites%)")
    List<InternshipOffer> findByCompanyAndFilters(@Param("company") Company company,
                                                  @Param("fantasyName") String companyName,
                                                  @Param("minRemuneration") Double minRemuneration,
                                                  @Param("maxRemuneration") Double maxRemuneration,
                                                  @Param("minWeeklyWorkload") Integer minWeeklyWorkload,
                                                  @Param("maxWeeklyWorkload") Integer maxWeeklyWorkload,
                                                  @Param("prerequisites") String prerequisites);
}