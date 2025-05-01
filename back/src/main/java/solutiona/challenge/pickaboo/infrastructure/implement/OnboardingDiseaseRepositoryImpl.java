package solutiona.challenge.pickaboo.infrastructure.implement;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import solutiona.challenge.pickaboo.domain.entity.OnboardingDisease;
import solutiona.challenge.pickaboo.domain.repository.OnboardingDiseaseRepository;
import solutiona.challenge.pickaboo.infrastructure.jpa.OnboardingDiseaseJpaRepository;

@Repository
@RequiredArgsConstructor
public class OnboardingDiseaseRepositoryImpl implements OnboardingDiseaseRepository {
    private final OnboardingDiseaseJpaRepository onboardingDiseaseJpaRepository;

    @Override
    public void saveAll(List<OnboardingDisease> onboardingDisease) {
      onboardingDiseaseJpaRepository.saveAll(onboardingDisease);
    }
}
