package solutiona.challenge.pickaboo.application.service.user;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solutiona.challenge.pickaboo.application.usecase.user.CreateOnboardingUserUseCase;
import solutiona.challenge.pickaboo.domain.entity.Allergy;
import solutiona.challenge.pickaboo.domain.entity.OnboardingDisease;
import solutiona.challenge.pickaboo.domain.entity.User;
import solutiona.challenge.pickaboo.domain.repository.AllergyRepository;
import solutiona.challenge.pickaboo.domain.repository.OnboardingDiseaseRepository;
import solutiona.challenge.pickaboo.domain.repository.UserRepository;
import solutiona.challenge.pickaboo.presentation.request.AllergyRequestDto;
import solutiona.challenge.pickaboo.presentation.request.CreateUserRequestDto;
import solutiona.challenge.pickaboo.presentation.request.OnboardingDiseaseRequestDto;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateOnboardingUserService implements CreateOnboardingUserUseCase {
    private final UserRepository userRepository;
    private final AllergyRepository allergyRepository;
    private final OnboardingDiseaseRepository onboardingDiseaseRepository;

    @Override
    public Boolean execute(CreateUserRequestDto createUserRequestDto, UUID userId){
        User user = userRepository.findById(userId);
        System.err.println("asdfasdf");

        user.updateUser(
                createUserRequestDto.location(),
                createUserRequestDto.language(),
                createUserRequestDto.birth(),
                createUserRequestDto.gender(),
                createUserRequestDto.skinType(),
                createUserRequestDto.bloodType()
        );
        System.err.println("asdfasdf");
        userRepository.save(user);

        List<Allergy> allergies = createUserRequestDto.allergyList().stream()
                .map(allergyRequestDto -> ofAllergy(allergyRequestDto, user))
                .toList();
        allergyRepository.saveAll(allergies);

        List<OnboardingDisease> onboardingDiseases = createUserRequestDto.onDiseaseList().stream()
                .map(onboardingDiseaseRequestDto -> ofDisease(onboardingDiseaseRequestDto, user))
                .toList();
        onboardingDiseaseRepository.saveAll(onboardingDiseases);

        return true;

    }

    private Allergy ofAllergy(AllergyRequestDto allergyRequestDto, User user) {
        return Allergy.builder()
                .description(allergyRequestDto.description())
                .user(user)
                .build();
    }

    private OnboardingDisease ofDisease(OnboardingDiseaseRequestDto onboardingDiseaseRequestDto, User user) {
        return OnboardingDisease.builder()
                .description(onboardingDiseaseRequestDto.description())
                .user(user)
                .build();
    }
}
