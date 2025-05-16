package solutiona.challenge.pickaboo.application.service.diagnosis;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solutiona.challenge.pickaboo.application.dto.diagnosis.ReadDiagnosisDetailResponseDto;
import solutiona.challenge.pickaboo.application.mapper.DiagnosisMapper;
import solutiona.challenge.pickaboo.application.usecase.diagnosis.ReadDiagnosisDetailUseCase;
import solutiona.challenge.pickaboo.domain.entity.Diagnosis;
import solutiona.challenge.pickaboo.domain.entity.User;
import solutiona.challenge.pickaboo.domain.repository.DiagnosisRepository;
import solutiona.challenge.pickaboo.domain.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadDiagnosisDetailService implements ReadDiagnosisDetailUseCase {
    private final UserRepository userRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisMapper diagnosisMapper;

    @Override
    public ReadDiagnosisDetailResponseDto execute(UUID userId, Long diagnosisId) {
        User user = userRepository.findById(userId);

        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId);

        switch (user.getLanguage()) {
            case "en":
                return diagnosisMapper.ofEDetail(diagnosis);
            case "ko":
            default:
                return diagnosisMapper.ofDetail(diagnosis);
        }
    }



}
