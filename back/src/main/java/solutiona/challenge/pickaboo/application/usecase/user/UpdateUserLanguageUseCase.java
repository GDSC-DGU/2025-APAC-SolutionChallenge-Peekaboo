package solutiona.challenge.pickaboo.application.usecase.user;

import java.util.UUID;
import solutiona.challenge.pickaboo.core.annotation.UseCase;

@UseCase
public interface UpdateUserLanguageUseCase {
    Boolean execute(String language, UUID userId);
}
