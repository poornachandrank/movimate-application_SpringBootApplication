package com.ds.moviemate.securityconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ds.moviemate.model.UserEntity;
import com.ds.moviemate.pinciple.UserPrinciple;
import com.ds.moviemate.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity customer = userRepository.findByEmail(username);
		
		if (customer==null) {

			throw new UsernameNotFoundException("please register and login");
		}
	
		return new UserPrinciple(customer);
	}
	
	
	
	
	
	
	
	
}
