package com.player.data.gameaddict.common.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.player.data.gameaddict.common.enums.ApplicationPermission.*;

public enum ApplicationRole {

    ADMIN(Sets.newHashSet(MANAGER_CREATE, MANAGER_READ, MANAGER_UPDATE, MANAGER_DELETE)),
    MANAGER(Sets.newHashSet(MANAGER_CREATE, MANAGER_READ, MANAGER_UPDATE));

    private final Set<ApplicationPermission> permissions;

    public Set<ApplicationPermission> getPermissions() {
        return this.permissions;
    }
    ApplicationRole(Set<ApplicationPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return  simpleGrantedAuthorities;
    }
}
