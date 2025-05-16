package solutiona.challenge.pickaboo.application.service.diagnosis;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solutiona.challenge.pickaboo.application.dto.diagnosis.DiagnosisPdfResponseDto;
import solutiona.challenge.pickaboo.application.usecase.diagnosis.CreateDiagnosisPdfUseCase;
import solutiona.challenge.pickaboo.domain.entity.Diagnosis;
import solutiona.challenge.pickaboo.domain.entity.Disease;
import solutiona.challenge.pickaboo.domain.entity.User;
import solutiona.challenge.pickaboo.domain.repository.DiagnosisRepository;
import solutiona.challenge.pickaboo.domain.repository.DiseaseRepository;
import solutiona.challenge.pickaboo.domain.repository.UserRepository;
import solutiona.challenge.pickaboo.infrastructure.diagnosisPdf.DiagnosisDrugsPdf;
import solutiona.challenge.pickaboo.infrastructure.diagnosisPdf.DiagnosisPdfDisease;
import solutiona.challenge.pickaboo.infrastructure.diagnosisPdf.DiagnosisPdfRequestDto;
import solutiona.challenge.pickaboo.infrastructure.diagnosisPdf.HttpDiagnosisPdf;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateDiagnosisPdfService implements CreateDiagnosisPdfUseCase {
    private final DiagnosisRepository diagnosisRepository;
    private final DiseaseRepository diseaseRepository;
    private final HttpDiagnosisPdf httpDiagnosisPdf;
    private final UserRepository userRepository;

    @Override
    public DiagnosisPdfResponseDto execute(UUID userId, Long diagnosisId, String lang) {
        User user = userRepository.findById(userId);
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId);

        Disease disease = diseaseRepository.findByDiagnosisAndRanking(diagnosis, 1L);

        DiagnosisPdfRequestDto diagnosisPdfRequestDto = DiagnosisPdfRequestDto.builder()
                .lang(lang)
                .name(user.getNickname())
                .sex(user.getGender())
                .location(user.getLocation())
                .skin_type(user.getSkinType())
                .birth(user.getBirth().toString())
                .blood_type(user.getBloodType())
                .symptom(diagnosis.getDescription())
                .affected_area(diagnosis.getPart())
                .disease_name(disease.getDiseaseConst().getEName())
                .ai_description(diagnosis.getEnCustomDescription())
                .allergies(allergyDto(user))
                .histories(historyDto(user))
                .build();

        return DiagnosisPdfResponseDto.builder()
                .url(httpDiagnosisPdf.getDIAGNOSIS_PDF_URI(diagnosisPdfRequestDto))
                .build();

    }
    private List<String> allergyDto(User user) {
        List<String> result = new ArrayList<>();
        user.getAllergies().stream()
                .forEach(allergy ->  result.add(allergy.getDescription()));
        return result;

    }

    private List<String> historyDto(User user) {
        List<String> result = new ArrayList<>();
        user.getOnboardingDiseases().stream()
                .forEach(onboardingDisease ->  result.add(onboardingDisease.getDescription()));
        return result;

    }

    private DiagnosisPdfDisease diseaseDto(Disease disease) {
        return DiagnosisPdfDisease.builder()
                .name(disease.getDiseaseConst().getEName())
                .ranking(disease.getDiseaseConst().getERating())
                .description(disease.getDiseaseConst().getEDescription())
                .type(disease.getDiseaseConst().getEType())
                .site(disease.getDiseaseConst().getESite())
                .reason(disease.getDiseaseConst().getEReason())
                .mild(disease.getDiseaseConst().getEMild())
                .severe(disease.getDiseaseConst().getESevere())
                .preventive(disease.getDiseaseConst().getEPreventive())
                .caution(disease.getDiseaseConst().getECaution())
                .build();
    }

}
