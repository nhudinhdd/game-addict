package com.player.data.gameaddict.security;

import com.player.data.gameaddict.auth.ApplicationUserService;
import io.netty.handler.codec.base64.Base64Encoder;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.player.data.gameaddict.common.enums.ApplicationPermission.*;
import static com.player.data.gameaddict.common.enums.ApplicationRole.ADMIN;
import static com.player.data.gameaddict.common.enums.ApplicationRole.MANAGER;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ApplicationSecurityConfig {

    private final ApplicationUserService userService;

    @Autowired
    public ApplicationSecurityConfig(ApplicationUserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/management/api/**").hasAuthority(MANAGER_READ.getPermission())
                        .requestMatchers(HttpMethod.POST, "/management/api/**").hasAnyAuthority(MANAGER_CREATE.getPermission())
                        .requestMatchers(HttpMethod.PUT, "/management/api/**").hasAnyAuthority(MANAGER_UPDATE.getPermission())
                        .requestMatchers(HttpMethod.DELETE, "/management/api/**").hasAnyAuthority(MANAGER_DELETE.getPermission())
                        .requestMatchers("/management/api/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                        .anyRequest().authenticated()
                )
                .authenticationProvider(daoAuthenticationProvider())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults());
        return http.build();
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
