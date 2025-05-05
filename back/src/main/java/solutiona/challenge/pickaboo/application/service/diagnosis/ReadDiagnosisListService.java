package solutiona.challenge.pickaboo.application.service.diagnosis;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solutiona.challenge.pickaboo.application.dto.diagnosis.DiseaseListResponseDto;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiagnosisListResponseDto;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiagnosisResponseDto;
import solutiona.challenge.pickaboo.application.usecase.diagnosis.ReadDiagnosisListUseCase;
import solutiona.challenge.pickaboo.domain.entity.Diagnosis;
import solutiona.challenge.pickaboo.domain.entity.User;
import solutiona.challenge.pickaboo.domain.repository.DiagnosisRepository;
import solutiona.challenge.pickaboo.domain.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadDiagnosisListService implements ReadDiagnosisListUseCase {
    private final UserRepository userRepository;
    private final DiagnosisRepository diagnosisRepository;


    @Override
    public ReadDiagnosisListResponseDto execute(UUID userId, String lang) {
        User user = userRepository.findById(userId);

        List<Diagnosis> diagnoses = diagnosisRepository.findByUser(user);

        //historyList
        List<ReadDiagnosisResponseDto> readDiagnosisResponseDtos = diagnoses.stream()
                .map(
                        diagnosis -> ReadDiagnosisResponseDto.builder()
                                .diagnosisId(diagnosis.getId())
                                .customDescription(lang.equals("ko") ? diagnosis.getKoCustomDescription() : diagnosis.getEnCustomDescription())
                                .createAt(diagnosis.getCreateAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                                .diseaseList(createList(diagnosis, lang))
                                .build()
                ).toList();



        return ReadDiagnosisListResponseDto.builder()
                .historyList(readDiagnosisResponseDtos)
                .build();

    }

    private List<DiseaseListResponseDto> createList(Diagnosis diagnosis, String lang) {

        switch (lang) {
            case "en" : {
                return diagnosis.getDiseases().stream()
                        .map(
                                disease -> DiseaseListResponseDto.builder()
                                        .diseaseId(disease.getId())
                                        .diseaseName(disease.getDiseaseConst().getEName())
                                        .ranking(disease.getRanking())
                                        .build()
                        ).toList();
            }
            case "ko":
            default: {
                return diagnosis.getDiseases().stream()
                        .map(
                                disease -> DiseaseListResponseDto.builder()
                                        .diseaseId(disease.getId())
                                        .diseaseName(disease.getDiseaseConst().getName())
                                        .ranking(disease.getRanking())
                                        .build()
                        ).toList();
            }
        }

    }
}
