package com.gerasimchuk.services;

import com.gerasimchuk.entities.User;
import com.gerasimchuk.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CustomUserDetailsService.class);

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDetails loadUserByUsername(String personalNumber) throws UsernameNotFoundException {
        //now just for tests we will get user by id
        User user = userRepository.getById(2);
        String username = user.getPersonalNumber();
        String password = user.getPassword();
        GrantedAuthority auth = new SimpleGrantedAuthority("MANAGER");
        Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
        set.add(auth);
        UserDetails details = new org.springframework.security.core.userdetails.User(username,password,set);
        return details;
    }
}
