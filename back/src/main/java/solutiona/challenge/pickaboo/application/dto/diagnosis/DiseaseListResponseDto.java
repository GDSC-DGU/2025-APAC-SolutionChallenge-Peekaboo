package solutiona.challenge.pickaboo.application.dto.diagnosis;

import lombok.Builder;

@Builder
public record DiseaseListResponseDto(
        Long diseaseId,
        String diseaseName,
        Long ranking
) {
}
