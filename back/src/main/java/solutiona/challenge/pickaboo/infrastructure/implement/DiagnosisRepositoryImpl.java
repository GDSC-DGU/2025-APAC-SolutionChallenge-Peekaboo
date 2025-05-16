package solutiona.challenge.pickaboo.infrastructure.implement;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import solutiona.challenge.pickaboo.core.exception.CustomException;
import solutiona.challenge.pickaboo.core.exception.ErrorCode;
import solutiona.challenge.pickaboo.domain.entity.Diagnosis;
import solutiona.challenge.pickaboo.domain.entity.User;
import solutiona.challenge.pickaboo.domain.repository.DiagnosisRepository;
import solutiona.challenge.pickaboo.infrastructure.jpa.DiagnosisJpaRepository;

@Repository
@RequiredArgsConstructor
public class DiagnosisRepositoryImpl implements DiagnosisRepository {
    private final DiagnosisJpaRepository diagnosisJpaRepository;

    @Override
    public List<Diagnosis> findByUser(User user) {
        return diagnosisJpaRepository.findByUser(user);
    }

    @Override
    public Diagnosis findById(Long diagnosisId) {
        return diagnosisJpaRepository.findById(diagnosisId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER)); // 질병 없음
    }

    @Override
    public void save(Diagnosis diagnosis) {
        diagnosisJpaRepository.save(diagnosis);
    }
}
