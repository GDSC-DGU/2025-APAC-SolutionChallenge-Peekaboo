package solutiona.challenge.pickaboo.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solutiona.challenge.pickaboo.domain.entity.Disease;

@Repository
public interface DiseaseJpaRepository extends JpaRepository<Disease, Long> {
}
