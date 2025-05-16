package solutiona.challenge.pickaboo.infrastructure.diagnosisAi;

import lombok.Builder;

@Builder
public record HttpDiagnosisList(
        String Id,
        String disease,
        String probability,
        String reason
) {
}
