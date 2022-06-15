package com.itmo.kotiki.detalis;

import com.itmo.kotiki.entity.UserinfoEntity;
import com.itmo.kotiki.repository.HumanRepository;
import com.itmo.kotiki.repository.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserinfoRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return new
                UserDetailsImpl(usersRepository.findByUsername(login)
                .orElseThrow(IllegalArgumentException::new));
    }
}
