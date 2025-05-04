package solutiona.challenge.pickaboo.infrastructure.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import solutiona.challenge.pickaboo.domain.entity.Diagnosis;
import solutiona.challenge.pickaboo.domain.entity.User;

public interface DiagnosisJpaRepository extends JpaRepository<Diagnosis, Long> {
    List<Diagnosis> findByUser(User user);
}
