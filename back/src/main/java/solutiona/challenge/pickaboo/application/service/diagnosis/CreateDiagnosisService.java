package solutiona.challenge.pickaboo.application.service.diagnosis;

import jakarta.transaction.Transactional;
import java.io.IOException;
import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiagnosisDetailResponseDto;
import solutiona.challenge.pickaboo.application.mapper.DiagnosisMapper;
import solutiona.challenge.pickaboo.application.usecase.diagnosis.CreateDiagnosisUseCase;
import solutiona.challenge.pickaboo.domain.entity.Diagnosis;
import solutiona.challenge.pickaboo.domain.entity.Disease;
import solutiona.challenge.pickaboo.domain.entity.User;
import solutiona.challenge.pickaboo.domain.repository.DiagnosisRepository;
import solutiona.challenge.pickaboo.domain.repository.DiseaseConstRepository;
import solutiona.challenge.pickaboo.domain.repository.DiseaseRepository;
import solutiona.challenge.pickaboo.domain.repository.UserRepository;
import solutiona.challenge.pickaboo.infrastructure.diagnosisAi.HttpDiagnosisAI;
import solutiona.challenge.pickaboo.infrastructure.diagnosisAi.HttpDiagnosisAIResponseDto;
import solutiona.challenge.pickaboo.presentation.request.DiagnosisDataRequestDto;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateDiagnosisService implements CreateDiagnosisUseCase {
    private final UserRepository userRepository;
    private final HttpDiagnosisAI httpDiagnosisAI;
    private final DiagnosisRepository diagnosisRepository;
    private final DiseaseConstRepository diseaseConstRepository;
    private final DiseaseRepository diseaseRepository;
    private final DiagnosisMapper diagnosisMapper;

    @Override
    public ReadDiagnosisDetailResponseDto execute(UUID userId,
                                                  String affectedArea,
                                                  String symptoms,
                                                  MultipartFile multipartFile) {
        User user = userRepository.findById(userId);
        HttpDiagnosisAIResponseDto httpDiagnosisAIResponseDto = httpDiagnosisAI.diagnosisAi(user.getId(), affectedArea, symptoms, multipartFile, user.getLanguage() );
        System.err.println(httpDiagnosisAIResponseDto.customDescription());
        Diagnosis newDiagnosis = Diagnosis.builder()
                .part(affectedArea)
                .description(symptoms)
                .koCustomDescription(httpDiagnosisAIResponseDto.customDescription())
                .enCustomDescription(httpDiagnosisAIResponseDto.customDescription())
                .user(user)
                .build();

        diagnosisRepository.save(newDiagnosis);

        AtomicLong i = new AtomicLong(1L);

        List<Disease> newDisease = httpDiagnosisAIResponseDto.result().stream()
                .map(httpDiagnosisList -> Disease.builder()
                        .percent(Long.parseLong(httpDiagnosisList.probability()))
                        .ranking(i.getAndIncrement())
                        .diseaseConst(diseaseConstRepository.findById(Long.parseLong(httpDiagnosisList.Id())))
                        .diagnosis(newDiagnosis)
                        .build())
                .toList();

        diseaseRepository.saveAll(newDisease);

        Diagnosis diagnosis = diagnosisRepository.findById(newDiagnosis.getId());

        switch (user.getLanguage()) {
            case "en":
                return diagnosisMapper.ofEDetailDiagnosis(diagnosis, newDisease);
            case "ko":
            default:
                return diagnosisMapper.ofDetail(diagnosis);
        }

    }
}
