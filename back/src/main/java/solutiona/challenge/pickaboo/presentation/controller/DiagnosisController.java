package solutiona.challenge.pickaboo.presentation.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solutiona.challenge.pickaboo.application.usecase.diagnosis.ReadDiagnosisDetailUseCase;
import solutiona.challenge.pickaboo.application.usecase.diagnosis.ReadDiagnosisListUseCase;
import solutiona.challenge.pickaboo.core.annotation.UserId;
import solutiona.challenge.pickaboo.core.common.CommonResponseDto;

@RestController
@RequestMapping("/api/v1/diagnosis/history")
@RequiredArgsConstructor
public class DiagnosisController {
    private final ReadDiagnosisListUseCase readDiagnosisListUseCase;
    private final ReadDiagnosisDetailUseCase readDiagnosisDetailUseCase;

    @GetMapping("")
    public CommonResponseDto<?> readList(
            @UserId UUID userId
    ) {
        return CommonResponseDto.ok(readDiagnosisListUseCase.execute(userId));
    }

    @GetMapping("/{diagnosisId}")
    public CommonResponseDto<?> readDetail(
            @UserId UUID userId,
            @PathVariable(name = "diagnosisId") Long diagnosisId
    ) {
        return CommonResponseDto.ok(readDiagnosisDetailUseCase.execute(userId, diagnosisId));
    }
}
