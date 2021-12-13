package com.example.springsecurityRaghu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springsecurityRaghu.dao.Userrepos;
import com.example.springsecurityRaghu.model.User;

@Service
@Transactional
public class MyUserservice {

	@Autowired
	public Userrepos userrepos;
	@Autowired
	public BCryptPasswordEncoder encoder;

	public User createuser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userrepos.save(user);
	}

	public void deleteuser(int id) {
		userrepos.deleteById(id);
	}

	public List<User> getallUsers() {
		return userrepos.findAll();
	}

	public User getoneUser(int id) {
		return userrepos.findById(id).get();
	}

	public User loaduserbyUsername(String username) {
		User user = userrepos.getUserByUsername(username);
//        user.setPassword(encoder.encode(user.getPassword()));
		return user;
	}
}
