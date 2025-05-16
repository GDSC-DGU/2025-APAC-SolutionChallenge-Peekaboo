package solutiona.challenge.pickaboo.infrastructure.diagnosisPdf;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

@Builder
public record DiagnosisPdfRequestDto(
        String lang,
        String name,
        String sex,
        String location,
        String skin_type,
        String birth,
        String blood_type,
        String symptom,
        String affected_area,
        String disease_name,
        String ai_description,
        List<String> allergies,
        List<String> histories
) {
}


/*

{
  "lang": "en",  // 번언어 지정 (ko, en, ja, zh-CN 등)
  "name": "김민수",
  "sex": "남성",
  "location": "서울시 강남구",
  "skin_type": "복합성",
  "birth": "1995-08-23",
  "blood_type": "AB",
  "symptom": "아파아파아파아파",
  "affected_area": "팔과 다리",
  "allergies": ["꽃가루", "해산물", "나"],
  "histories": ["2022년 습진 치료", "2023년 여드름 치료"],
  "disease_name": "여드름성 피부염",
  "ai_description": "환자는 T-zone 부위에 염증성 여드름이 집중적으로 분포하고 있으며, 모공 각화증이 동반된 상태입니다. 항균 치료와 각질 관리를 동시에 진행해야 합니다."
}



{
"patient_name": "Hong Gil-dong",
"doctor_name": "Dr. Kim",
"disease": {
"name": "Cold",
"ranking": "Common",
"description": "Viral upper respiratory infection",
"type": "Virus",
"site": "Nose, throat",
"reason": "Exposure to cold virus",
"mild": "Rest and drink fluids",
"severe": "Hospital visit required",
"preventive": "Hand washing",
"caution": "Avoid overexertion"
},
"symptoms": ["Runny nose", "Cough", "Sore throat"],
"drugs": ["Tylenol 500mg, 3 times a day", "Vitamin C 1000mg"]
}
 */