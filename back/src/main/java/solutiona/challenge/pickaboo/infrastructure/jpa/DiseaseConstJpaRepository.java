package solutiona.challenge.pickaboo.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import solutiona.challenge.pickaboo.domain.entity.DiseaseConst;

public interface DiseaseConstJpaRepository extends JpaRepository<DiseaseConst, Long> {
}
