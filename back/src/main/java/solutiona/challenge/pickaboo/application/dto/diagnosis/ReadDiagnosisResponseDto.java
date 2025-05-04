package solutiona.challenge.pickaboo.application.dto.diagnosis;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record ReadDiagnosisResponseDto(
        Long diagnosisId,
        String customDescription,
        LocalDate createAt,
        List<DiseaseListResponseDto> diseaseList

) {
}
/*
"diagnosisId": 1,

 */