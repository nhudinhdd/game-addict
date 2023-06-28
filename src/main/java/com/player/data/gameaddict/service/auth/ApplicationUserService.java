package com.player.data.gameaddict.service.auth;

import com.player.data.gameaddict.config.security.ApplicationUser;
import com.player.data.gameaddict.entity.User;
import com.player.data.gameaddict.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.player.data.gameaddict.common.enums.ApplicationRole.ADMIN;
import static com.player.data.gameaddict.common.enums.ApplicationRole.MANAGER;

@Service
public class  ApplicationUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public ApplicationUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<User> userOptional = userRepository.findByUserName(username);
        if (userOptional.isEmpty()) throw new UsernameNotFoundException("User not found");
        User user = userOptional.get();
        List<SimpleGrantedAuthority> grantedAuthorities;
        if (ADMIN.name().equalsIgnoreCase(user.getRole())) {
            grantedAuthorities = ADMIN.getGrantedAuthorities();
        } else {
            grantedAuthorities = MANAGER.getGrantedAuthorities();
        }
        return new ApplicationUser(userOptional.get(), grantedAuthorities);
    }
}
