package solutiona.challenge.pickaboo.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import solutiona.challenge.pickaboo.domain.entity.Drug;

public interface DrugJpaRepository extends JpaRepository<Drug, Long> {
}
