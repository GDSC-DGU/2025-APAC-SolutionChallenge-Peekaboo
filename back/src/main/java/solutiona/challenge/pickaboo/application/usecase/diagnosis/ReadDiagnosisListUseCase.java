package solutiona.challenge.pickaboo.application.usecase.diagnosis;

import java.util.UUID;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiagnosisListResponseDto;
import solutiona.challenge.pickaboo.core.annotation.UseCase;

@UseCase
public interface ReadDiagnosisListUseCase {
    ReadDiagnosisListResponseDto execute(UUID userId, String lang);
}
