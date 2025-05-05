package solutiona.challenge.pickaboo.application.dto.diagnosis;

import java.util.List;
import lombok.Builder;

@Builder
public record ReadDiagnosisListResponseDto(
        List<ReadDiagnosisResponseDto> historyList
) {
}
