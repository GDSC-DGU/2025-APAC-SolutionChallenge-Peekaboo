package solutiona.challenge.pickaboo.application.usecase.oauth;

import solutiona.challenge.pickaboo.application.dto.oauth.LoginResponseDto;
import solutiona.challenge.pickaboo.core.annotation.UseCase;

@UseCase
public interface LoginByGoogleUseCase {
    LoginResponseDto execute(String accessToken);
}
