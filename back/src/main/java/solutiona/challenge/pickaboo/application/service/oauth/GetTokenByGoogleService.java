package solutiona.challenge.pickaboo.application.service.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solutiona.challenge.pickaboo.application.usecase.oauth.GetTokenByGoogleUseCase;
import solutiona.challenge.pickaboo.core.util.OAuth2Util;

@Service
@RequiredArgsConstructor
public class GetTokenByGoogleService implements GetTokenByGoogleUseCase {
    private final OAuth2Util oAuth2Util;

    @Override
    public String execute(String authorizationCode) {
        return oAuth2Util.getGoogleAccessToken(authorizationCode);
    }
}
