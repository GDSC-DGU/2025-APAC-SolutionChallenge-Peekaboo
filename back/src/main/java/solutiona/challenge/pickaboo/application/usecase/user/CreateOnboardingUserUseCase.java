package solutiona.challenge.pickaboo.application.usecase.user;

import java.util.UUID;
import solutiona.challenge.pickaboo.core.annotation.UseCase;
import solutiona.challenge.pickaboo.presentation.request.CreateUserRequestDto;

@UseCase
public interface CreateOnboardingUserUseCase {
    Boolean execute(CreateUserRequestDto createUserRequestDto, UUID userId);
}
