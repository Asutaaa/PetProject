package com.itmo.kotiki.detalis;


import com.itmo.kotiki.entity.UserinfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserinfoEntity user;

    public UserDetailsImpl(UserinfoEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = user.getRole().getNum();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole);
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
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
    public String getPassword() {
        passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(user.getPassword()));
        return passwordEncoder.encode(user.getPassword()) ;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserinfoEntity getUser() {
        return user;
    }
}

