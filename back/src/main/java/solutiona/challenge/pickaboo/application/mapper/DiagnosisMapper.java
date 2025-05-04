package solutiona.challenge.pickaboo.application.mapper;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiagnosisDetailResponseDto;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiseaseConstResponseDto;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDrugsResponseDto;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadSymptomsResponseDto;
import solutiona.challenge.pickaboo.domain.entity.Diagnosis;
import solutiona.challenge.pickaboo.domain.entity.Disease;

@Service
@Transactional
public class DiagnosisMapper {
    public ReadDiagnosisDetailResponseDto ofDetail(Diagnosis diagnosis) {
        return ReadDiagnosisDetailResponseDto.builder()
                .customDescription(diagnosis.getKoCustomDescription())
                .diseaseList(ofDiseaseConst(diagnosis))
                .build();
    }

    public List<ReadDiseaseConstResponseDto> ofDiseaseConst(Diagnosis diagnosis) {
        return diagnosis.getDiseases().stream()
                .map(disease -> ReadDiseaseConstResponseDto.builder()
                        .diseaseId(disease.getId())
                        .diseaseName(disease.getDiseaseConst().getName())
                        .ranking(disease.getRanking())
                        .percent(disease.getPercent())
                        .description(disease.getDiseaseConst().getDescription())
                        .type(disease.getDiseaseConst().getType())
                        .site(disease.getDiseaseConst().getSite())
                        .reason(disease.getDiseaseConst().getReason())
                        .mild(disease.getDiseaseConst().getMild())
                        .severe(disease.getDiseaseConst().getSevere())
                        .preventive(disease.getDiseaseConst().getPreventive())
                        .caution(disease.getDiseaseConst().getCaution())
                        .symptoms(ofSymptoms(disease))
                        .drugs(ofDrugs(disease))
                        .build()
                ).toList();
    }

    public List<ReadSymptomsResponseDto> ofSymptoms(Disease disease) {
        return disease.getDiseaseConst().getSymptoms().stream()
                .map(symptoms -> ReadSymptomsResponseDto.builder()
                        .name(symptoms.getName())
                        .build())
                .toList();
    }

    public List<ReadDrugsResponseDto> ofDrugs(Disease disease) {
        return disease.getDiseaseConst().getDrugs().stream()
                .map(drug -> ReadDrugsResponseDto.builder()
                        .name(drug.getName())
                        .efficacy(drug.getEfficacy())
                        .build())
                .toList();
    }
}
