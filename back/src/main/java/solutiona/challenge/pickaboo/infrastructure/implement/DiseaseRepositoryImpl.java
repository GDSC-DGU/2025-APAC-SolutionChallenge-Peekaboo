package solutiona.challenge.pickaboo.infrastructure.implement;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import solutiona.challenge.pickaboo.core.exception.CustomException;
import solutiona.challenge.pickaboo.core.exception.ErrorCode;
import solutiona.challenge.pickaboo.domain.entity.Diagnosis;
import solutiona.challenge.pickaboo.domain.entity.Disease;
import solutiona.challenge.pickaboo.domain.repository.DiseaseRepository;
import solutiona.challenge.pickaboo.infrastructure.jpa.DiseaseJpaRepository;

@Repository
@RequiredArgsConstructor
public class DiseaseRepositoryImpl implements DiseaseRepository {
    private final DiseaseJpaRepository diseaseJpaRepository;

    @Override
    public void saveAll(List<Disease> disease) {
        diseaseJpaRepository.saveAll(disease);
    }

    @Override
    public Disease findByDiagnosisAndRanking(Diagnosis diagnosis, Long ranking) {
        return  diseaseJpaRepository.findByDiagnosisAndRanking(diagnosis, ranking)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER)); // 질병 이력이 존재하지 않음
    }
}
