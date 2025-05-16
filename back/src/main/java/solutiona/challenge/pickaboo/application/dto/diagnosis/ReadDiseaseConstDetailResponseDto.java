package solutiona.challenge.pickaboo.application.dto.diagnosis;

import java.util.List;
import lombok.Builder;

@Builder
public record ReadDiseaseConstDetailResponseDto(
        String diseaseName,
        Long rating,
        String description,
        String type,
        String site,
        String reason,
        String mild,
        String severe,
        String preventive,
        String caution,
        List<ReadSymptomsResponseDto> symptoms,
        List<ReadDrugsResponseDto> drugs
) {
}
