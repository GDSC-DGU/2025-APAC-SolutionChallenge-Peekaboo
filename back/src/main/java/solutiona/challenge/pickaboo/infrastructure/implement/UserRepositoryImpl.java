package solutiona.challenge.pickaboo.infrastructure.implement;

import java.util.Optional;
import org.springframework.data.repository.query.Param;
import solutiona.challenge.pickaboo.core.exception.CustomException;
import solutiona.challenge.pickaboo.core.exception.ErrorCode;
import solutiona.challenge.pickaboo.domain.entity.User;
import solutiona.challenge.pickaboo.domain.type.EProvider;
import solutiona.challenge.pickaboo.infrastructure.jpa.UserJpaRepository;
import solutiona.challenge.pickaboo.domain.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import solutiona.challenge.pickaboo.infrastructure.jpa.UserJpaRepository.UserSecurityForm;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User findByLoginId(String loginId) {
        return userJpaRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public User findById(UUID id) {
        return userJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public Optional<UserSecurityForm> findFormById(UUID id) {
        return userJpaRepository.findFormById(id);
    }

    @Override
    public Optional<UserSecurityForm> findFormByIdAndRefreshToken(UUID id, String refreshToken) {
        return userJpaRepository.findFormByIdAndRefreshToken(id, refreshToken);
    }

    @Override
    public Optional<UserSecurityForm> findFormBySerialIdAndProvider(String serialId, EProvider provider) {
        return userJpaRepository.findFormBySerialIdAndProvider(serialId, provider);
    }

    @Override
    public void updateRefreshToken(UUID id, String refreshToken) {
        userJpaRepository.updateRefreshToken(id, refreshToken);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }





}
