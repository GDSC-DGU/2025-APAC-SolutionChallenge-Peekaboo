package solutiona.challenge.pickaboo.infrastructure.diagnosisPdf;

import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import solutiona.challenge.pickaboo.core.exception.CustomException;
import solutiona.challenge.pickaboo.core.exception.ErrorCode;

@Component
@Slf4j
public class HttpDiagnosisPdf {
    @Value("${diagnosis.pdf.uri}")
    private String DIAGNOSIS_PDF_URI;

    private final RestClient restClient = RestClient.create();

    public String getDIAGNOSIS_PDF_URI(DiagnosisPdfRequestDto diagnosisPdfRequestDto) {
        Map<String, Object> response;

        try {
            response = Objects.requireNonNull(restClient.post()
                    .uri(DIAGNOSIS_PDF_URI)
                    .headers(httpHeaders -> {
                        httpHeaders.set("Content-Type", "application/json");
                    })
                    .body(diagnosisPdfRequestDto)
                    .retrieve()
                    .toEntity(Map.class).getBody());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR);
        }
        System.err.println(response);

        return response.get("url").toString();
    }
}
