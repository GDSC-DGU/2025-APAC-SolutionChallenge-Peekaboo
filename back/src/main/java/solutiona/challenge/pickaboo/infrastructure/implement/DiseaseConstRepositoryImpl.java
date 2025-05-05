package solutiona.challenge.pickaboo.infrastructure.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import solutiona.challenge.pickaboo.core.exception.CustomException;
import solutiona.challenge.pickaboo.core.exception.ErrorCode;
import solutiona.challenge.pickaboo.domain.entity.DiseaseConst;
import solutiona.challenge.pickaboo.domain.repository.DiseaseConstRepository;
import solutiona.challenge.pickaboo.infrastructure.jpa.DiseaseConstJpaRepository;

@Repository
@RequiredArgsConstructor
public class DiseaseConstRepositoryImpl implements DiseaseConstRepository {
    private final DiseaseConstJpaRepository diseaseConstJpaRepository;


    @Override
    public DiseaseConst findById(Long constId) {
        return diseaseConstJpaRepository.findById(constId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER)); // 질병 없음
    }
}
