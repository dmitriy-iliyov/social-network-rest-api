package com.example.socialnetworkrestapi.security;

import com.example.socialnetworkrestapi.models.entitys.PostEntity;
import com.example.socialnetworkrestapi.models.entitys.Role;
import com.example.socialnetworkrestapi.models.entitys.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailsImplementation implements UserDetails {

    private Long id;
    private String name;
    private String password;
    private String email;
    private Instant createDate;
    private Role role;
    private int postCount;

    public static UserDetailsImplementation build(UserEntity userEntity){
        return new UserDetailsImplementation(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                userEntity.getCreateDate(),
                userEntity.getRole(),
                userEntity.getPosts().size());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
