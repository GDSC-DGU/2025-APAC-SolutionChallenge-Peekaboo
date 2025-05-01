package solutiona.challenge.pickaboo.application.service.oauth;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solutiona.challenge.pickaboo.application.dto.JwtTokenDto;
import solutiona.challenge.pickaboo.application.dto.LoginResponseDto;
import solutiona.challenge.pickaboo.application.dto.OAuth2UserInfoResponseDto;
import solutiona.challenge.pickaboo.application.usecase.oauth.LoginByGoogleUseCase;
import solutiona.challenge.pickaboo.core.util.JwtUtil;
import solutiona.challenge.pickaboo.core.util.OAuth2Util;
import solutiona.challenge.pickaboo.core.util.PasswordUtil;
import solutiona.challenge.pickaboo.domain.entity.User;
import solutiona.challenge.pickaboo.domain.repository.UserRepository;
import solutiona.challenge.pickaboo.domain.type.EProvider;
import solutiona.challenge.pickaboo.domain.type.ERole;
import solutiona.challenge.pickaboo.infrastructure.jpa.UserJpaRepository;

@Service
@RequiredArgsConstructor
public class LoginByKakaoService implements LoginByGoogleUseCase {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;
    private final OAuth2Util oAuth2Util;
    private final JwtUtil jwtUtil;

    private String userFlag;

    @Override
    @Transactional
    public LoginResponseDto execute(String accessToken) {
        Map<String, String> userInfo = oAuth2Util.getGoogleUserInfo(accessToken);

        String serialId = userInfo.get("id");
        UserJpaRepository.UserSecurityForm userSecurityForm = userRepository.findFormBySerialIdAndProvider(serialId, EProvider.KAKAO)
                .orElseGet(() -> {
                    User user = userRepository.save(
                            User.builder()
                                    .serialId(serialId)
                                    .provider(EProvider.KAKAO)
                                    .role(ERole.USER)
                                    .nickname(userInfo.get("nickname"))
                                    .email(userInfo.get("email"))
                                    .password(bCryptPasswordEncoder.encode(PasswordUtil.generateRandomPassword()))
                                    .profile(userInfo.get("profile"))
                                    .build()
                    );
                    userFlag = "";
                    return UserJpaRepository.UserSecurityForm.of(user);
                });

        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(
                userSecurityForm.getId(),
                userSecurityForm.getRole()
        );

        userRepository.updateRefreshToken(userSecurityForm.getId(), jwtTokenDto.refreshToken());

        return LoginResponseDto.of(jwtTokenDto, "Asdf");
    }

}
