package com.example.springsecurityRaghu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.springsecurityRaghu.config.jwt.JwtUtil;
import com.example.springsecurityRaghu.config.service.CustomUserdetailsService;
import com.example.springsecurityRaghu.model.Jwtrequest;

@RestController
@RequestMapping("/take/")
public class Testcontroller {
	@Autowired
	private JwtUtil jwtutil;
	@Autowired
	AuthenticationManager manager;
	@Autowired

	private CustomUserdetailsService userderCustomUserdetailsservice;

	@Autowired
	JwtUtil myutil;

	@RequestMapping("/taketoken")
	public ModelAndView gototoken(@ModelAttribute("jwtrequest") Jwtrequest jwtrequest) {

		return new ModelAndView("Testpage");
	}

	@PostMapping("/generatetoken")
	public ModelAndView returntest(@ModelAttribute("jwtrequest") Jwtrequest jwtrequest) {
		try {

			Authentication authenticate = manager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtrequest.getUsername(), jwtrequest.getPassword()));

		} catch (Exception e) {
			System.out.println("Username not Found ");
		}

		UserDetails userDetails = userderCustomUserdetailsservice.loadUserByUsername(jwtrequest.getUsername());
		String token = jwtutil.generateToken(userDetails);
//		System.out.println(jwtutil.getUsernameFromToken(token));
//		System.out.println(jwtutil.getExpirationDateFromToken(token));
		return new ModelAndView("Testpage", "token", token);

	}
}
