package solutiona.challenge.pickaboo.domain.repository;

import java.util.List;
import solutiona.challenge.pickaboo.domain.entity.Diagnosis;
import solutiona.challenge.pickaboo.domain.entity.Disease;

public interface DiseaseRepository {
    void saveAll(List<Disease> disease);
    Disease findByDiagnosisAndRanking(Diagnosis diagnosis, Long ranking);
}
