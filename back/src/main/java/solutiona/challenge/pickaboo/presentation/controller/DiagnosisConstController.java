package solutiona.challenge.pickaboo.presentation.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solutiona.challenge.pickaboo.application.usecase.ReadDiagnosisConstUseCase;
import solutiona.challenge.pickaboo.application.usecase.diagnosis.ReadDiagnosisDetailUseCase;
import solutiona.challenge.pickaboo.core.annotation.UserId;
import solutiona.challenge.pickaboo.core.common.CommonResponseDto;

@RestController
@RequestMapping("/api/v1/diagnosis/const")
@RequiredArgsConstructor
public class DiagnosisConstController {
    private final ReadDiagnosisConstUseCase readDiagnosisConstUseCase;

    @GetMapping("/{constId}")
    public CommonResponseDto<?> readConst(
            @PathVariable(name = "constId") Long constId,
            @UserId UUID userId
    ) {
        return CommonResponseDto.ok(readDiagnosisConstUseCase.execute(constId, userId));
    }
}
