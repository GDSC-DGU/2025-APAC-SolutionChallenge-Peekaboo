package solutiona.challenge.pickaboo.core.util;

import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import solutiona.challenge.pickaboo.core.exception.CustomException;
import solutiona.challenge.pickaboo.core.exception.ErrorCode;

@Component
public class OAuth2Util {
    @Value("${spring.security.oauth2.client.provider.google.authorization-uri}")
    private String GOOGLE_AUTHORIZATION_URL;

    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String GOOGLE_TOKEN_URL;

    @Value("${spring.security.oauth2.client.provider.google.user-info-uri}")
    private String GOOGLE_USERINFO_URL;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String GOOGLE_REDIRECT_URL;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String GOOGLE_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String GOOGLE_CLIENT_SECRET;

    private final RestClient restClient = RestClient.create();

    public String getGoogleRedirectUrl() {
        return GOOGLE_AUTHORIZATION_URL
                + "?client_id=" + GOOGLE_CLIENT_ID +
                "&redirect_uri=" + GOOGLE_REDIRECT_URL +
                "&response_type=code" +
                "&scope=profile%20email";
    }

    public String getGoogleAccessToken(String authorizationCode) {
        Map<String, Object> response;

        try {
            response = Objects.requireNonNull(restClient.post()
                    .uri(GOOGLE_TOKEN_URL)
                    .headers(httpHeaders -> {
                        httpHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                    })
                    .body("grant_type=authorization_code&client_id=" + GOOGLE_CLIENT_ID + "&client_secret=" + GOOGLE_CLIENT_SECRET + "&code=" + authorizationCode + "&redirect_uri=" + GOOGLE_REDIRECT_URL)
                    .retrieve()
                    .toEntity(Map.class).getBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR);
        }

        return response.get("access_token").toString();
    }

    public Map<String, String> getGoogleUserInfo(String accessToken) {
        Map<String, Object> response;

        try {
            response = Objects.requireNonNull(restClient.post()
                    .uri(GOOGLE_USERINFO_URL)
                    .headers(httpHeaders -> {
                        httpHeaders.setBearerAuth(accessToken);
                        httpHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                    })
                    .retrieve()
                    .toEntity(Map.class).getBody());
        } catch (Exception e) {
            throw new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR);
        }

        System.out.println(response);

//        Map<String, Object> googleAccount = (Map<String, Object>) response.get("google_account");
//        Map<String, String> profile = (Map<String, String>) googleAccount.get("profile");

        String id = response.get("sub").toString();
        String nickname = response.get("name").toString();
        String email = response.get("email").toString();
        String profile = response.get("picture").toString();

        if (response.get("sub").toString() == null)
            throw new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR);

        return Map.of(
                "id", id,
                "nickname", nickname,
                "email", email,
                "profile", profile
        );
    }
}

