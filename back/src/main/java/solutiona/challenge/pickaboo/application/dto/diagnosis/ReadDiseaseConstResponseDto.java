package solutiona.challenge.pickaboo.application.dto.diagnosis;

import java.util.List;
import lombok.Builder;

@Builder
public record ReadDiseaseConstResponseDto(
        Long diseaseId,
        String diseaseName,
        Long ranking,
        Long percent,
        String description,
        String type,
        String site,
        String reason,
        String mild,
        String severe,
        String preventive,
        String caution,
        List<ReadSymptomsResponseDto> symptoms,
        List<ReadDrugsResponseDto> drugs
) {
}

/*
"diseaseList": [
	       {
		       "diseaseId": 1,
		       "diseaseName": "땀띠",
		       "ranking": 1, // 질병 진단 랭킹
		       "percent": 50, //질병 진단 퍼센트,
		       "rating": 1, // 질병의 위험 등급?
		       "description": "땀띠는 뭐시기 윕니다.", // 질병 설명
		       "type": "유형 -> 종류",
		       "site": "유형 -> 발병위치",
		       "reason": "유형 -> 원인",
		       "mild": "응급처치 -> 경증",
		       "severe": "응급처치 -> 중증",
		       "preventive": "예방 방법",
		       "caution": "주의 사항",
		       "symptoms": [
			       {
				       "name": "주요증상"
			       },
			       {
				       "name": "주요증상"
			       }
		       ],
		       "drugs": [
			       {
				       "name": "약명",
				       "efficacy": "효능"
			       },
			       {
				       "name": "약명",
				       "efficacy": "효능"
			       }
		       ]
 */