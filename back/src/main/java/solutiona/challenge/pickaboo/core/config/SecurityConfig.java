package solutiona.challenge.pickaboo.core.config;

import solutiona.challenge.pickaboo.core.constant.Constants;
import solutiona.challenge.pickaboo.core.security.filter.JwtAuthenticationFilter;
import solutiona.challenge.pickaboo.core.security.filter.JwtExceptionFilter;
import solutiona.challenge.pickaboo.core.security.handler.JwtAccessDeniedHandler;
import solutiona.challenge.pickaboo.core.security.handler.JwtEntryPoint;
import solutiona.challenge.pickaboo.core.security.service.CustomUserDetailService;
import solutiona.challenge.pickaboo.core.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final CustomUserDetailService customUserDetailService;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtEntryPoint jwtEntryPoint;
    private final JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(Constants.NO_NEED_AUTH_URLS.toArray(String[]::new)).permitAll()
                                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/users/**").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .authenticationEntryPoint(jwtEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .addFilterBefore(new JwtAuthenticationFilter(customUserDetailService, jwtUtil),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(
                        new JwtExceptionFilter(),
                        JwtAuthenticationFilter.class)
                .build();
    }
}
