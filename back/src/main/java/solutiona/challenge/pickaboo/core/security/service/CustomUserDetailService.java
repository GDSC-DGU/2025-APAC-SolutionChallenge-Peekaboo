package solutiona.challenge.pickaboo.core.security.service;

import solutiona.challenge.pickaboo.core.exception.CustomException;
import solutiona.challenge.pickaboo.core.exception.ErrorCode;
import solutiona.challenge.pickaboo.core.security.info.UserPrincipal;
import solutiona.challenge.pickaboo.domain.entity.User;
import solutiona.challenge.pickaboo.infrastructure.jpa.UserJpaRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserJpaRepository userRepositoryImpl;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }

    public UserDetails loadUserById(UUID id) {
        User user = userRepositoryImpl.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        return UserPrincipal.create(user);
    }

}
