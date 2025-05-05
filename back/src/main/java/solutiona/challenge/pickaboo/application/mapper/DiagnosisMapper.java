package solutiona.challenge.pickaboo.application.mapper;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiagnosisDetailResponseDto;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiseaseConstDetailResponseDto;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiseaseConstResponseDto;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDrugsResponseDto;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadSymptomsResponseDto;
import solutiona.challenge.pickaboo.domain.entity.Diagnosis;
import solutiona.challenge.pickaboo.domain.entity.Disease;
import solutiona.challenge.pickaboo.domain.entity.DiseaseConst;

@Service
@Transactional
public class DiagnosisMapper {
    public ReadDiagnosisDetailResponseDto ofDetail(Diagnosis diagnosis) {
        return ReadDiagnosisDetailResponseDto.builder()
                .customDescription(diagnosis.getKoCustomDescription())
                .diseaseList(ofDiseaseConst(diagnosis))
                .build();
    }

    private List<ReadDiseaseConstResponseDto> ofDiseaseConst(Diagnosis diagnosis) {
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
                        .symptoms(ofSymptoms(disease.getDiseaseConst()))
                        .drugs(ofDrugs(disease.getDiseaseConst()))
                        .build()
                ).toList();
    }

    private List<ReadSymptomsResponseDto> ofSymptoms(DiseaseConst diseaseConst) {
        return diseaseConst.getSymptoms().stream()
                .map(symptoms -> ReadSymptomsResponseDto.builder()
                        .name(symptoms.getName())
                        .build())
                .toList();
    }

    private List<ReadDrugsResponseDto> ofDrugs(DiseaseConst diseaseConst) {
        return diseaseConst.getDrugs().stream()
                .map(drug -> ReadDrugsResponseDto.builder()
                        .name(drug.getName())
                        .efficacy(drug.getEfficacy())
                        .build())
                .toList();
    }






    public ReadDiagnosisDetailResponseDto ofEDetail(Diagnosis diagnosis) {
        return ReadDiagnosisDetailResponseDto.builder()
                .customDescription(diagnosis.getEnCustomDescription())
                .diseaseList(ofEDiseaseConst(diagnosis))
                .build();
    }

    private List<ReadDiseaseConstResponseDto> ofEDiseaseConst(Diagnosis diagnosis) {
        return diagnosis.getDiseases().stream()
                .map(disease -> ReadDiseaseConstResponseDto.builder()
                        .diseaseId(disease.getId())
                        .diseaseName(disease.getDiseaseConst().getEName())
                        .ranking(disease.getRanking())
                        .percent(disease.getPercent())
                        .description(disease.getDiseaseConst().getEDescription())
                        .type(disease.getDiseaseConst().getEType())
                        .site(disease.getDiseaseConst().getESite())
                        .reason(disease.getDiseaseConst().getEReason())
                        .mild(disease.getDiseaseConst().getEMild())
                        .severe(disease.getDiseaseConst().getESevere())
                        .preventive(disease.getDiseaseConst().getEPreventive())
                        .caution(disease.getDiseaseConst().getECaution())
                        .symptoms(ofESymptoms(disease.getDiseaseConst()))
                        .drugs(ofEDrugs(disease.getDiseaseConst()))
                        .build()
                ).toList();
    }

    private List<ReadSymptomsResponseDto> ofESymptoms(DiseaseConst diseaseConst) {
        return diseaseConst.getSymptoms().stream()
                .map(symptoms -> ReadSymptomsResponseDto.builder()
                        .name(symptoms.getEName())
                        .build())
                .toList();
    }

    private List<ReadDrugsResponseDto> ofEDrugs(DiseaseConst diseaseConst) {
        return diseaseConst.getDrugs().stream()
                .map(drug -> ReadDrugsResponseDto.builder()
                        .name(drug.getEName())
                        .efficacy(drug.getEEfficacy())
                        .build())
                .toList();
    }


    public ReadDiseaseConstDetailResponseDto ofConst(DiseaseConst diseaseConst, String lang) {
        switch (lang) {
            case "ko" : {
                return ReadDiseaseConstDetailResponseDto.builder()
                        .diseaseName(diseaseConst.getName())
                        .rating(diseaseConst.getRating())
                        .description(diseaseConst.getDescription())
                        .type(diseaseConst.getType())
                        .site(diseaseConst.getSite())
                        .reason(diseaseConst.getReason())
                        .mild(diseaseConst.getMild())
                        .severe(diseaseConst.getSevere())
                        .preventive(diseaseConst.getPreventive())
                        .caution(diseaseConst.getCaution())
                        .symptoms(ofSymptoms(diseaseConst))
                        .drugs(ofDrugs(diseaseConst))
                        .build();
            }
            case "en":
            default: {
                return ReadDiseaseConstDetailResponseDto.builder()
                        .diseaseName(diseaseConst.getName())
                        .rating(diseaseConst.getRating())
                        .description(diseaseConst.getEDescription())
                        .type(diseaseConst.getEType())
                        .site(diseaseConst.getESite())
                        .reason(diseaseConst.getEReason())
                        .mild(diseaseConst.getEMild())
                        .severe(diseaseConst.getESevere())
                        .preventive(diseaseConst.getEPreventive())
                        .caution(diseaseConst.getECaution())
                        .symptoms(ofESymptoms(diseaseConst))
                        .drugs(ofEDrugs(diseaseConst))
                        .build();
            }
        }
    }
}
