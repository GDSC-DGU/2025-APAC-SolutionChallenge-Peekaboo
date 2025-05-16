package solutiona.challenge.pickaboo.presentation.controller;

import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import solutiona.challenge.pickaboo.application.usecase.diagnosis.CreateDiagnosisPdfUseCase;
import solutiona.challenge.pickaboo.application.usecase.diagnosis.CreateDiagnosisUseCase;
import solutiona.challenge.pickaboo.application.usecase.diagnosis.ReadDiagnosisDetailUseCase;
import solutiona.challenge.pickaboo.application.usecase.diagnosis.ReadDiagnosisListUseCase;
import solutiona.challenge.pickaboo.core.annotation.UserId;
import solutiona.challenge.pickaboo.core.common.CommonResponseDto;
import solutiona.challenge.pickaboo.presentation.request.DiagnosisDataRequestDto;

@RestController
@RequestMapping("/api/v1/diagnosis")
@RequiredArgsConstructor
public class DiagnosisController {
    private final ReadDiagnosisListUseCase readDiagnosisListUseCase;
    private final ReadDiagnosisDetailUseCase readDiagnosisDetailUseCase;
    private final CreateDiagnosisUseCase createDiagnosisUseCase;
    private final CreateDiagnosisPdfUseCase createDiagnosisPdfUseCase;

    @GetMapping("/history")
    public CommonResponseDto<?> readList(
            @UserId UUID userId
    ) {
        return CommonResponseDto.ok(readDiagnosisListUseCase.execute(userId));
    }

    @GetMapping("/history/{diagnosisId}")
    public CommonResponseDto<?> readDetail(
            @UserId UUID userId,
            @PathVariable(name = "diagnosisId") Long diagnosisId
    ) {
        return CommonResponseDto.ok(readDiagnosisDetailUseCase.execute(userId, diagnosisId));
    }

    @PostMapping("")
    public CommonResponseDto<?> create(
            @UserId UUID userId,
            @RequestPart(name = "image")MultipartFile multipartFile,
            @RequestPart(name = "affectedArea") String affectedArea,
            @RequestPart(name = "symptoms") String symptoms

    )  {
        return CommonResponseDto.ok(createDiagnosisUseCase.execute(userId, affectedArea, symptoms, multipartFile));
    }

    @GetMapping("/pdf/{diagnosisId}")
    public CommonResponseDto<?> getPdf(
            @UserId UUID userId,
            @PathVariable(name = "diagnosisId") Long diagnosisId,
            @RequestParam(name = "lang") String lang
    ) {
        return CommonResponseDto.ok(createDiagnosisPdfUseCase.execute(userId, diagnosisId, lang));
    }
}
