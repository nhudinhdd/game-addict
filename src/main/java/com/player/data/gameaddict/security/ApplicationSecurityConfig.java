package com.player.data.gameaddict.security;

import com.player.data.gameaddict.common.enums.ApplicationRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.player.data.gameaddict.common.enums.ApplicationPermission.*;
import static com.player.data.gameaddict.common.enums.ApplicationRole.ADMIN;
import static com.player.data.gameaddict.common.enums.ApplicationRole.MANAGER;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ApplicationSecurityConfig {

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
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("hach")
                .password(encoder.encode("hacheery"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails user = User.withUsername("user")
                .password(encoder.encode("pwd1"))
                .authorities(MANAGER.getGrantedAuthorities())
                .roles()
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
