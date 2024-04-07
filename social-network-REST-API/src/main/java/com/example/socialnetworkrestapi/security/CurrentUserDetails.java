package com.example.socialnetworkrestapi.security;

import com.example.socialnetworkrestapi.entitys.PostEntity;
import com.example.socialnetworkrestapi.entitys.Role;
import com.example.socialnetworkrestapi.entitys.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class CurrentUserDetails implements UserDetails {

    private Long id;
    private String name;
    private String password;
    private String email;
    private Instant createDate;
    private Role role;
    private List<PostEntity> posts;

    public static CurrentUserDetails build(UserEntity userEntity){
        return new CurrentUserDetails(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                userEntity.getCreateDate(),
                userEntity.getRole(),
                userEntity.getPosts());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
