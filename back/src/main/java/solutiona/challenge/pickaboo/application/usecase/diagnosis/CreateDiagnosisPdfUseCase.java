package solutiona.challenge.pickaboo.application.usecase.diagnosis;

import java.util.UUID;
import solutiona.challenge.pickaboo.application.dto.diagnosis.DiagnosisPdfResponseDto;
import solutiona.challenge.pickaboo.core.annotation.UseCase;

@UseCase
public interface CreateDiagnosisPdfUseCase {
    DiagnosisPdfResponseDto execute(UUID userId, Long diangosisId, String lang);
}
