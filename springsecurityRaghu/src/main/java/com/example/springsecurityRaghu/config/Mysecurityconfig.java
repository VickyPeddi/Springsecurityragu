package com.example.springsecurityRaghu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.springsecurityRaghu.config.jwt.Jwtauthenticationentrypoint;
import com.example.springsecurityRaghu.config.service.CustomUserdetailsService;
import com.example.springsecurityRaghu.util.Jwtfilter;

@EnableWebSecurity

public class Mysecurityconfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserdetailsService userdetailsService;
	@Autowired
	private Jwtauthenticationentrypoint jwtauthenticationentrypoint;
	@Autowired
	private Jwtfilter jwtfilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/user/user", "/user/").permitAll().antMatchers("/take/taketoken")
				.authenticated().antMatchers("/take/generatetoken").authenticated().antMatchers("/testuser/")
				.hasAnyAuthority("ADMIN")
				// if controller has 1000 restendpoints we need not to check all of them
//                .anyRequest().hasRole("USER")
				// login details
				.and().formLogin().defaultSuccessUrl("/user/").and()
				// logout details
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
//exceptiondetails
				.and().exceptionHandling().authenticationEntryPoint(jwtauthenticationentrypoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.exceptionHandling().accessDeniedPage("/user/");
		http.addFilterAfter(jwtfilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager getmanager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("nanda").password("{noop}nanda").authorities("ADMIN").and()
//                .withUser("pavan").password("{noop}pavan").authorities("USER");
		auth.userDetailsService(userdetailsService).passwordEncoder(encoder());
	}

}
