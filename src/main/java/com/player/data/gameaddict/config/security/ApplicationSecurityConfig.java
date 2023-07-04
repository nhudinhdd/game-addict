package com.player.data.gameaddict.config.security;

import com.player.data.gameaddict.config.security.jwt.JwtUserNameAndPasswordFilter;
import com.player.data.gameaddict.service.auth.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.player.data.gameaddict.common.enums.ApplicationPermission.*;
import static com.player.data.gameaddict.common.enums.ApplicationRole.ADMIN;
import static com.player.data.gameaddict.common.enums.ApplicationRole.MANAGER;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfig {

    private final ApplicationUserService userService;
    private final JwtUserNameAndPasswordFilter jwtUserNameAndPasswordFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/auth/login").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/management/**").hasAuthority(MANAGER_READ.getPermission())
//                        .requestMatchers(HttpMethod.POST, "/api/management/**").hasAnyAuthority(MANAGER_CREATE.getPermission())
//                        .requestMatchers(HttpMethod.PUT, "/api/management/**").hasAnyAuthority(MANAGER_UPDATE.getPermission())
//                        .requestMatchers(HttpMethod.DELETE, "api/management/**").hasAnyAuthority(MANAGER_DELETE.getPermission())
//                        .requestMatchers("/api//management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
//                        .requestMatchers("/api/auth/register").hasAuthority(ADMIN_CREATE.getPermission())
//                        .requestMatchers("/api/auth/register").hasRole(ADMIN.name())
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().authenticated()

                )
                .authenticationProvider(daoAuthenticationProvider())
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtUserNameAndPasswordFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return  provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
