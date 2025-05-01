package solutiona.challenge.pickaboo.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import solutiona.challenge.pickaboo.domain.entity.Symptoms;

public interface SymptomsJpaRepository extends JpaRepository<Symptoms, Long> {
}
