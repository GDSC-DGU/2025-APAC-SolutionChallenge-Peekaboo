package solutiona.challenge.pickaboo.infrastructure.diagnosisAi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;
import solutiona.challenge.pickaboo.core.exception.CustomException;
import solutiona.challenge.pickaboo.core.exception.ErrorCode;
import co.nlighten.shortuuid.ShortUuid;
import solutiona.challenge.pickaboo.presentation.request.DiagnosisDataRequestDto;

@Slf4j
@Component
public class HttpDiagnosisAI {

    @Value("${diagnosis.ai.uri}")
    private String DIAGNOSIS_AI_URI;

    private final RestClient restClient = RestClient.create();

//    public HttpDiagnosisAIResponseDto diagnosisAi(UUID userId,
//                                                  String affectedArea,
//                                                  String symptoms,
//                                                  MultipartFile multipartFile)
//             {
//        Map<String, Object> response;
//
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        try {
//            body.add("image", new InputStreamResource(multipartFile.getInputStream()) {
//                @Override
//                public String getFilename() {
//                    return multipartFile.getOriginalFilename();
//                }
//                @Override
//                public long contentLength() throws IOException {
//                    return multipartFile.getSize();
//                }
//            });
//        } catch (IOException e) {
//            log.error("파일 스트림 처리 중 오류 발생", e);
//            throw new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR);
//        }
//
//        body.add("userId", userId);
//        body.add("affected_area", affectedArea);
//        body.add("symptoms", symptoms);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
//        //RestClient restClient = RestClient.create(DIAGNOSIS_URI);
//        // 3. 파일 리소스 준비
//        try {
//            response = Objects.requireNonNull(restClient.post()
//                    .uri(DIAGNOSIS_AI_URI)
//                    .headers(httpHeaders -> {
//                        httpHeaders.set("Content-Type", "application/json");
//                    })
//                    .body(requestEntity)
//                    .retrieve()
//                    .toEntity(Map.class).getBody());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            throw new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR);
//        }
//        log.error(response.toString());
//        List<Map<String, Object>> dataList = (List<Map<String, Object>>) response.get("data");
//
//        List<HttpDiagnosisList> result = new ArrayList<>();
//
//        int flag = 0;
//        String customDescription = "";
//        for(Map<String, Object> item : dataList) {
//            result.add(
//                    HttpDiagnosisList.builder()
//                            .Id(item.get("id").toString())
//                            .probability(item.get("probability").toString())
//                            .disease(item.get("disease").toString())
//                            .reason(item.get("reason").toString())
//                            .build()
//            );
//            if(flag == 0)
//                customDescription = item.get("reason").toString();
//
//        }
//
//        return HttpDiagnosisAIResponseDto.builder()
//                .result(result)
//                .customDescription(customDescription)
//                .build();
//
//    }
public HttpDiagnosisAIResponseDto diagnosisAi(UUID userId,
                                              String affectedArea,
                                              String symptoms,
                                              MultipartFile multipartFile,
                                              String lang) {
    Map<String, Object> response;

    // MultipartFile -> File 변환
    File file;
    try {
        file = convert(multipartFile)
                .orElseThrow(() -> new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR));
    } catch (IOException e) {
        log.error("파일 변환 중 오류 발생", e);
        System.err.println("asdf" + e.getMessage());
        throw new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR);
    }

    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    // FileSystemResource로 감싸서 multipart에 추가
    body.add("image", new FileSystemResource(file));
    body.add("userId", userId.toString());
    body.add("affected_area", affectedArea);
    body.add("symptoms", symptoms);


//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

//    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

    try {
        response = Objects.requireNonNull(restClient.post()
                .uri(DIAGNOSIS_AI_URI + "?lang=" + lang)
                .body(body)
                .retrieve()
                .toEntity(Map.class)
                .getBody());
    } catch (Exception e) {
        System.err.println(e.getMessage());
        log.error(e.getMessage());
        throw new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR);
    }
    file.delete();
    // 이하 응답 처리 동일
    log.error(response.toString());
    List<Map<String, Object>> dataList = (List<Map<String, Object>>) response.get("data");

    List<HttpDiagnosisList> result = new ArrayList<>();
    int flag = 0;
    String customDescription = "";
    for (Map<String, Object> item : dataList) {
        result.add(
                HttpDiagnosisList.builder()
                        .Id(item.get("id").toString())
                        .probability(item.get("probability").toString())
                        .disease(item.get("disease").toString())
                        .reason(item.get("reason").toString())
                        .build()
        );
        if (flag == 0)
            customDescription = item.get("reason").toString();
    }

    return HttpDiagnosisAIResponseDto.builder()
            .result(result)
            .customDescription(customDescription)
            .build();
}

    // MultipartFile -> File 변환 함수
    private Optional<File> convert(MultipartFile file) throws IOException {
        String uniqueFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File convertFile = new File(System.getProperty("user.dir") + "/" + uniqueFileName);
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }


}
