package solutiona.challenge.pickaboo.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import solutiona.challenge.pickaboo.application.usecase.oauth.GetTokenByGoogleUseCase;
import solutiona.challenge.pickaboo.application.usecase.oauth.LoginByGoogleUseCase;
import solutiona.challenge.pickaboo.application.usecase.oauth.RedirectToGoogleLoginUseCase;
import solutiona.challenge.pickaboo.core.common.CommonResponseDto;
import solutiona.challenge.pickaboo.core.constant.Constants;
import solutiona.challenge.pickaboo.core.exception.CustomException;
import solutiona.challenge.pickaboo.core.exception.ErrorCode;
import solutiona.challenge.pickaboo.core.util.HeaderUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {
    private final LoginByGoogleUseCase loginByGoogleUseCase;
    private final RedirectToGoogleLoginUseCase redirectToGoogleLoginUseCase;
    private final GetTokenByGoogleUseCase getTokenByGoogleUseCase;

    @PostMapping("/login/google")
    public CommonResponseDto<?> loginByGoogle(
            HttpServletRequest request
    ) {
        String accessToken = HeaderUtil.refineHeader(request, Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_AUTHORIZATION_HEADER));

        return CommonResponseDto.ok(loginByGoogleUseCase.execute(accessToken));
    }

    @GetMapping("/login/google")
    public ResponseEntity<?> loginByGoogle(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        // redirectToKakaoLoginUseCase로 리다이렉트 URL을 받아온다.
        String redirectUrl = redirectToGoogleLoginUseCase.execute();

        // ResponseEntity로 리다이렉트 URL을 반환한다.
        return ResponseEntity.status(302).header("Location", redirectUrl).build();
    }

    @GetMapping("/login/google/callback")
    public CommonResponseDto<?> callbackByKakao(
            @RequestParam("code") String authorizationCode
    ) {
        return CommonResponseDto.ok(getTokenByGoogleUseCase.execute(authorizationCode));
    }
}
