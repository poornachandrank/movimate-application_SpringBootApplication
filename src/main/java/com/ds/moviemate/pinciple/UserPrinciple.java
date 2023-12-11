package com.ds.moviemate.pinciple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ds.moviemate.model.RoleEntity;
import com.ds.moviemate.model.UserEntity;

public class UserPrinciple implements UserDetails {

	private static final long serialVersionUID = 3136550751607020144L;
	private String username;
	private String password;
	private transient RoleEntity authorities;

	public UserPrinciple(UserEntity userEntity) {
		this.username = userEntity.getEmail();
		this.password = userEntity.getPassword();
		this.authorities = userEntity.getRole();

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<>();
		auth.add(new SimpleGrantedAuthority(authorities.getRoleName()));
		return auth;
	}

	

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
