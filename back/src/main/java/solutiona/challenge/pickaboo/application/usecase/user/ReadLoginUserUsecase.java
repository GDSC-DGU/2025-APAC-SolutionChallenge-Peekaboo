package solutiona.challenge.pickaboo.application.usecase.user;

import solutiona.challenge.pickaboo.application.dto.oauth.JwtTokenDto;
import solutiona.challenge.pickaboo.core.annotation.UseCase;
import solutiona.challenge.pickaboo.presentation.request.LoginUserRequestDto;

@UseCase
public interface ReadLoginUserUsecase {
    JwtTokenDto execute(LoginUserRequestDto loginUserRequestDto);
}
