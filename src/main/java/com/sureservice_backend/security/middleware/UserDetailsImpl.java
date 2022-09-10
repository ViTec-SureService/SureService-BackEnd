package com.sureservice_backend.security.middleware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sureservice_backend.security.domain.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;


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

    @Override
    public boolean equals(Object other) {
        if(this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) other;
        return Objects.equals(id, user.id);
    }

    public static UserDetailsImpl build(User user) {
        List<String> roles = new ArrayList<String>();
        roles.add(user.getRol().getName().name());
        List<GrantedAuthority> authorities = roles.stream().map(
                        role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
    }

}