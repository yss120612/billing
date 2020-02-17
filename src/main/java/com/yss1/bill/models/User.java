package com.yss1.bill.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private Set<Role> roles = new HashSet<Role>();
	private String username;
	private String password;
	private boolean locked = false;
	private boolean enabled = true;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}

	public List<String> getRoleNames()
	{
		List<String> res = new ArrayList<>();
		for (Role r:roles) {
			res.add(r.getRoleName());
		}
		return res;
	}
	
	public void clearRoles() {
		roles.clear();
	}

	public boolean addRole(Role r) {
		if (!this.hasRole(r)) {
			roles.add(r);
			return true;
		}
		return false;
	}
	
	
	public boolean hasRole(Role r) {
		for (Role ro : roles) {
			if (ro.getId() == r.getId()) {
				return true;
			}
		}
		return false;
	}

	public boolean hasRole(String name) {
		for (Role ro : roles) {
			if (ro.getRoleName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public void setAuthorities(Set<Role> ro) {
		roles = ro;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

}
