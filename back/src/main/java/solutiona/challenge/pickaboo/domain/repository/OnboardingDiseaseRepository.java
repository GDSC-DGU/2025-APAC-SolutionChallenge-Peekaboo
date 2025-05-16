package solutiona.challenge.pickaboo.domain.repository;

import java.util.List;
import solutiona.challenge.pickaboo.domain.entity.OnboardingDisease;

public interface OnboardingDiseaseRepository {
    void saveAll(List<OnboardingDisease> onboardingDisease);
}
