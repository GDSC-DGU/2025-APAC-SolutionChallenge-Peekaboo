package solutiona.challenge.pickaboo.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import solutiona.challenge.pickaboo.domain.entity.Diagnosis;

public interface DiagnosisJpaRepository extends JpaRepository<Diagnosis, Long> {
}
