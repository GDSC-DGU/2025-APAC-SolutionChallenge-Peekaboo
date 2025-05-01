package solutiona.challenge.pickaboo.presentation.request;

import java.time.LocalDate;
import java.util.List;

public record CreateUserRequestDto(
        String location,
        String language,
        LocalDate birth,
        String gender,
        String bloodType,
        String skinType,
        List<AllergyRequestDto> allergyList,
        List<OnboardingDiseaseRequestDto> onDiseaseList


) {
}
