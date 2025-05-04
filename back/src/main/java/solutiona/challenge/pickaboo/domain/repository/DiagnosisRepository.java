package solutiona.challenge.pickaboo.domain.repository;

import java.util.List;
import solutiona.challenge.pickaboo.domain.entity.Diagnosis;
import solutiona.challenge.pickaboo.domain.entity.User;

public interface DiagnosisRepository {
    List<Diagnosis> findByUser(User user);
    Diagnosis findById(Long diagnosisId);
}
