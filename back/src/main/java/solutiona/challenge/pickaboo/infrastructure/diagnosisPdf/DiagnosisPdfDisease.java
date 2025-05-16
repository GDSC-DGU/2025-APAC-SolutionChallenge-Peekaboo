package solutiona.challenge.pickaboo.infrastructure.diagnosisPdf;

import lombok.Builder;

@Builder
public record DiagnosisPdfDisease(
        String name,
        Long ranking,
        String description,
        String type,
        String site,
        String reason,
        String mild,
        String severe,
        String preventive,
        String caution
) {
}

/*
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