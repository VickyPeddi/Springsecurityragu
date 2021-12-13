package com.example.springsecurityRaghu.dao;

import com.example.springsecurityRaghu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepos extends JpaRepository<User, Integer> {
    public User getUserByUsername(String username) throws UsernameNotFoundException;
}
