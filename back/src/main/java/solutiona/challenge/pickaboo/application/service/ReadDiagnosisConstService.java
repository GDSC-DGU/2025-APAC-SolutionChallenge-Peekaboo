package solutiona.challenge.pickaboo.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiseaseConstDetailResponseDto;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiseaseConstResponseDto;
import solutiona.challenge.pickaboo.application.mapper.DiagnosisMapper;
import solutiona.challenge.pickaboo.application.usecase.ReadDiagnosisConstUseCase;
import solutiona.challenge.pickaboo.domain.entity.DiseaseConst;
import solutiona.challenge.pickaboo.domain.entity.User;
import solutiona.challenge.pickaboo.domain.repository.DiseaseConstRepository;
import solutiona.challenge.pickaboo.domain.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadDiagnosisConstService implements ReadDiagnosisConstUseCase {
    private final DiagnosisMapper diagnosisMapper;
    private final UserRepository userRepository;
    private final DiseaseConstRepository diseaseConstRepository;

    public ReadDiseaseConstDetailResponseDto execute(Long constId, UUID userId) {
        User user = userRepository.findById(userId);

        DiseaseConst diseaseConst = diseaseConstRepository.findById(constId);
        return diagnosisMapper.ofConst(diseaseConst, user.getLanguage());
    }
}
