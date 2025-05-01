package solutiona.challenge.pickaboo.application.usecase;

import solutiona.challenge.pickaboo.application.dto.JwtTokenDto;
import solutiona.challenge.pickaboo.core.annotation.UseCase;
import solutiona.challenge.pickaboo.core.security.JwtDto;
import solutiona.challenge.pickaboo.presentation.request.LoginUserRequestDto;

@UseCase
public interface ReadLoginUserUsecase {
    JwtTokenDto execute(LoginUserRequestDto loginUserRequestDto);
}
