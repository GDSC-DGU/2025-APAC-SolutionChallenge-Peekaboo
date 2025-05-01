package solutiona.challenge.pickaboo.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ERole {
    USER("USER", "ROLE_USER"),
    ADMIN("ADMIN", "ROLE_ADMIN");

    private final String name;
    private final String securityName;
}
