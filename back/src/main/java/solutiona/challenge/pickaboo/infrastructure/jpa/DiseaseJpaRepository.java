package solutiona.challenge.pickaboo.infrastructure.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solutiona.challenge.pickaboo.domain.entity.Diagnosis;
import solutiona.challenge.pickaboo.domain.entity.Disease;

@Repository
public interface DiseaseJpaRepository extends JpaRepository<Disease, Long> {
    Optional<Disease> findByDiagnosisAndRanking(Diagnosis diagnosis, Long ranking);
}
