package solutiona.challenge.pickaboo.infrastructure.diagnosisPdf;

import lombok.Builder;

@Builder
public record DiagnosisDrugsPdf(
        String name,
        String efficacy
) {
}
