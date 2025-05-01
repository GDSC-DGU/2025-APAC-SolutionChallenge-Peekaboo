package solutiona.challenge.pickaboo.presentation.controller;

import solutiona.challenge.pickaboo.core.common.CommonResponseDto;
import solutiona.challenge.pickaboo.application.usecase.ReadLoginUserUsecase;
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

    @PostMapping("/login")
    public CommonResponseDto<?> userLogin(
            @RequestBody LoginUserRequestDto loginUserRequestDto
    ) {
        return CommonResponseDto.ok(readLoginUserUsecase.execute(loginUserRequestDto));
    }

}
