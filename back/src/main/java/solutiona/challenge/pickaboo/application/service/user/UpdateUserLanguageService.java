package solutiona.challenge.pickaboo.application.service.user;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solutiona.challenge.pickaboo.application.usecase.user.UpdateUserLanguageUseCase;
import solutiona.challenge.pickaboo.domain.entity.User;
import solutiona.challenge.pickaboo.domain.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateUserLanguageService implements UpdateUserLanguageUseCase {
    private final UserRepository userRepository;

    @Override
    public Boolean execute(String language, UUID userId) {
        User user = userRepository.findById(userId);

        user.updateLanguage(language);

        return true;
    }
}
