package solutiona.challenge.pickaboo.application.dto.diagnosis;

import java.util.List;
import lombok.Builder;

@Builder
public record ReadDiagnosisDetailResponseDto(
        String customDescription,
        Long diagnosisId,
        List<ReadDiseaseConstResponseDto> diseaseList
) {
}

/*
"customDescription": "맞춤형 정보를 의미합니다.",

	       },
 */