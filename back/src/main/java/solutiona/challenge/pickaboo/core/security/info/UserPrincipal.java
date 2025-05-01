package solutiona.challenge.pickaboo.core.security.info;


import solutiona.challenge.pickaboo.domain.entity.User;
import solutiona.challenge.pickaboo.domain.type.ERole;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {
    @Getter
    private final UUID uuid;
    @Getter
    private final ERole userRole;
    private final Collection<? extends GrantedAuthority> authorities;


    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return UserPrincipal.builder()
                .uuid(user.getId())
                .userRole(user.getRole())
                .authorities(authorities)
                .build();
    }


    @Override
    public String getUsername() {
        return uuid.toString();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
