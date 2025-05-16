package solutiona.challenge.pickaboo.domain.repository;

import java.util.List;
import solutiona.challenge.pickaboo.domain.entity.Allergy;

public interface AllergyRepository {
    void saveAll(List<Allergy> allergy);
}
