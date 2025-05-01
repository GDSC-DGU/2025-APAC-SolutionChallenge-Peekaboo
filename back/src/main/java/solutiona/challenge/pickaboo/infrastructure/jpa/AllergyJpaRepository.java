package solutiona.challenge.pickaboo.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import solutiona.challenge.pickaboo.domain.entity.Allergy;

public interface AllergyJpaRepository extends JpaRepository<Allergy, Long> {
}
