package solutiona.challenge.pickaboo.presentation.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import solutiona.challenge.pickaboo.application.usecase.user.CreateOnboardingUserUseCase;
import solutiona.challenge.pickaboo.application.usecase.user.UpdateUserLanguageUseCase;
import solutiona.challenge.pickaboo.core.annotation.UserId;
import solutiona.challenge.pickaboo.core.common.CommonResponseDto;
import solutiona.challenge.pickaboo.application.usecase.user.ReadLoginUserUsecase;
import solutiona.challenge.pickaboo.presentation.request.CreateUserRequestDto;
import solutiona.challenge.pickaboo.presentation.request.LoginUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final ReadLoginUserUsecase readLoginUserUsecase;
    private final CreateOnboardingUserUseCase createOnboardingUserUseCase;
    private final UpdateUserLanguageUseCase updateUserLanguageUseCase;

    @PostMapping("/login")
    public CommonResponseDto<?> userLogin(
            @RequestBody LoginUserRequestDto loginUserRequestDto
    ) {
        return CommonResponseDto.ok(readLoginUserUsecase.execute(loginUserRequestDto));
    }

    @PostMapping("")
    public CommonResponseDto<?> create(
            @RequestBody CreateUserRequestDto createUserRequestDto,
            @UserId UUID userId
    ) {
        return CommonResponseDto.ok(createOnboardingUserUseCase.execute(createUserRequestDto, userId));
    }

    @PatchMapping("/language")
    public CommonResponseDto<?> updateLanguage(
            @RequestParam(name = "lang") String language,
            @UserId UUID userId
    ) {
        return CommonResponseDto.ok(updateUserLanguageUseCase.execute(language, userId));
    }
}
