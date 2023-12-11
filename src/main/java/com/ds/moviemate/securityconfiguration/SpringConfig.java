package com.ds.moviemate.securityconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration

public class SpringConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailService userDetailService;

//	
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
		daoAuth.setUserDetailsService(userDetailService);
	    daoAuth.setPasswordEncoder(bcrypt());
		return daoAuth;
	}

	@Bean
	PasswordEncoder bcrypt() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  http.csrf().disable()
	        .authorizeRequests()
	           .antMatchers("/home","/Signup","/img/**","/movies","/cinemas","/cinemas/movies/*","/BookingShows*","/CinemaDetails*","/js/**").permitAll()
	           
	            .antMatchers("/User_Home").hasAuthority("CUSTOMER")
	            .antMatchers("/Dashboard","/allBookings").hasAnyAuthority("ADMIN")
	            .anyRequest().authenticated()
	        .and()
	        .formLogin()
	            .loginPage("/home")
	            .loginProcessingUrl("/login")
	            .successHandler((request, response, authentication) -> {
	                for (GrantedAuthority authority : authentication.getAuthorities()) {
	                    if (authority.getAuthority().equals("ADMIN")) {
	                        response.sendRedirect("/Dashboard");
	                        return;
	                    }
	                }
	                response.sendRedirect("/User_Home");
	            })
	            .permitAll()
	        .and()
	        .logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/logout-page")
	            .invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .permitAll();
	}


	
}
