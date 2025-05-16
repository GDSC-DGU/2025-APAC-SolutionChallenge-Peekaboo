package solutiona.challenge.pickaboo.application.service.oauth;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solutiona.challenge.pickaboo.application.usecase.oauth.RedirectToGoogleLoginUseCase;
import solutiona.challenge.pickaboo.core.util.OAuth2Util;

@Service
@RequiredArgsConstructor
public class RedirectToKakaoLoginService implements RedirectToGoogleLoginUseCase {
    private final OAuth2Util oAuth2Util;

    @Override
    public String execute() {
        return oAuth2Util.getGoogleRedirectUrl();
    }
}
