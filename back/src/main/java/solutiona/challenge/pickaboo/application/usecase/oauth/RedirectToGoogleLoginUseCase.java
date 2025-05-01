package solutiona.challenge.pickaboo.application.usecase.oauth;

import solutiona.challenge.pickaboo.core.annotation.UseCase;

@UseCase
public interface RedirectToGoogleLoginUseCase {
    String execute();
}
