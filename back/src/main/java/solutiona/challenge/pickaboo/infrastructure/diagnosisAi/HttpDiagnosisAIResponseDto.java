package solutiona.challenge.pickaboo.infrastructure.diagnosisAi;

import java.lang.invoke.CallSite;
import java.util.List;
import lombok.Builder;

@Builder
public record HttpDiagnosisAIResponseDto(
        String customDescription,
        List<HttpDiagnosisList> result

) {
}
