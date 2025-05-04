package solutiona.challenge.pickaboo.application.service;

import solutiona.challenge.pickaboo.application.dto.oauth.JwtTokenDto;
import solutiona.challenge.pickaboo.core.util.JwtUtil;
import solutiona.challenge.pickaboo.application.usecase.user.ReadLoginUserUsecase;
import solutiona.challenge.pickaboo.domain.entity.User;
import solutiona.challenge.pickaboo.domain.type.ERole;
import solutiona.challenge.pickaboo.domain.repository.UserRepository;
import solutiona.challenge.pickaboo.presentation.request.LoginUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReadLoginUserService implements ReadLoginUserUsecase {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public JwtTokenDto execute(LoginUserRequestDto loginUserRequestDto) {
        User user = userRepository.findByLoginId(loginUserRequestDto.loginId());

        return jwtUtil.generateTokens(user.getId(), ERole.USER);

    }
}
