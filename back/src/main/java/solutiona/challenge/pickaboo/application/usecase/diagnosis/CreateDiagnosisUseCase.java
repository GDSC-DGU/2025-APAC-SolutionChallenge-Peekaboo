package solutiona.challenge.pickaboo.application.usecase.diagnosis;

import java.io.IOException;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiagnosisDetailResponseDto;
import solutiona.challenge.pickaboo.core.annotation.UseCase;
import solutiona.challenge.pickaboo.domain.entity.Diagnosis;
import solutiona.challenge.pickaboo.presentation.request.DiagnosisDataRequestDto;

@UseCase
public interface CreateDiagnosisUseCase {
    ReadDiagnosisDetailResponseDto execute(UUID userId,
                                           String affectedArea,
                                           String symptoms,
                                           MultipartFile multipartFile
    );
}
