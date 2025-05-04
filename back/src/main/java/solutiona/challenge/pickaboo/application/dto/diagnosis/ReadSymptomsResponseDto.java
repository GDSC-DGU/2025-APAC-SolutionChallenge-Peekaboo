package solutiona.challenge.pickaboo.application.dto.diagnosis;

import lombok.Builder;

@Builder
public record ReadSymptomsResponseDto(
        String name
) {
}
