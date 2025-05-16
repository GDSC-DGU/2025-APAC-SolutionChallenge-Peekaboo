package solutiona.challenge.pickaboo.application.usecase;

import java.util.UUID;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiseaseConstDetailResponseDto;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiseaseConstResponseDto;
import solutiona.challenge.pickaboo.core.annotation.UseCase;

@UseCase
public interface ReadDiagnosisConstUseCase {
    ReadDiseaseConstDetailResponseDto execute(Long constId, UUID userId);
}
