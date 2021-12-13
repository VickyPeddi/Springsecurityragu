package com.example.springsecurityRaghu.config.service;

import com.example.springsecurityRaghu.model.User;
import com.example.springsecurityRaghu.service.MyUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class CustomUserdetailsService implements UserDetailsService {
    @Autowired
    private MyUserservice repos;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repos.loaduserbyUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("user name not found exception");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
