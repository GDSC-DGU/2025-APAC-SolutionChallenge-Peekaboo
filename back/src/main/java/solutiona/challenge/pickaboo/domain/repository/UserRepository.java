package solutiona.challenge.pickaboo.domain.repository;

import java.util.Optional;
import org.springframework.data.repository.query.Param;
import solutiona.challenge.pickaboo.domain.entity.User;
import java.util.UUID;
import solutiona.challenge.pickaboo.domain.type.EProvider;
import solutiona.challenge.pickaboo.domain.type.ERole;
import solutiona.challenge.pickaboo.infrastructure.jpa.UserJpaRepository;
import solutiona.challenge.pickaboo.infrastructure.jpa.UserJpaRepository.UserSecurityForm;

public interface UserRepository {

    User findByLoginId(String loginId);

    User findById(UUID id);
    User save(User user);


    Optional<UserSecurityForm> findFormById(UUID id);
    Optional<UserSecurityForm> findFormByIdAndRefreshToken(UUID id, String refreshToken);
    Optional<UserSecurityForm> findFormBySerialIdAndProvider(String serialId, EProvider provider);
    void updateRefreshToken(UUID id, String refreshToken);




}
