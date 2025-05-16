package solutiona.challenge.pickaboo.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import solutiona.challenge.pickaboo.domain.entity.OnboardingDisease;

public interface OnboardingDiseaseJpaRepository extends JpaRepository<OnboardingDisease, Long> {
}
