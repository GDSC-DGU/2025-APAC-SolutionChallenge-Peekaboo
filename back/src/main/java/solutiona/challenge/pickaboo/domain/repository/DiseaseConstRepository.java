package solutiona.challenge.pickaboo.domain.repository;

import solutiona.challenge.pickaboo.domain.entity.DiseaseConst;

public interface DiseaseConstRepository {
    DiseaseConst findById(Long constId);
}
