package solutiona.challenge.pickaboo.application.usecase.diagnosis;

import java.util.UUID;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiagnosisDetailResponseDto;
import solutiona.challenge.pickaboo.core.annotation.UseCase;

@UseCase
public interface ReadDiagnosisDetailUseCase {
    ReadDiagnosisDetailResponseDto execute(UUID userId, Long diagnosisId);
}
