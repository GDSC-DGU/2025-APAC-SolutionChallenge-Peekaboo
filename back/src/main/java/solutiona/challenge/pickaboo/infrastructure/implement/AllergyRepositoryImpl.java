package solutiona.challenge.pickaboo.infrastructure.implement;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import solutiona.challenge.pickaboo.domain.entity.Allergy;
import solutiona.challenge.pickaboo.domain.repository.AllergyRepository;
import solutiona.challenge.pickaboo.infrastructure.jpa.AllergyJpaRepository;

@Repository
@RequiredArgsConstructor
public class AllergyRepositoryImpl implements AllergyRepository {
    private final AllergyJpaRepository allergyJpaRepository;

    @Override
    public void saveAll(List<Allergy> allergy) {
        allergyJpaRepository.saveAll(allergy);
    }
}
