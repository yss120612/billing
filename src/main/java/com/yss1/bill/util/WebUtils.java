package com.yss1.bill.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.yss1.bill.models.User;

public class WebUtils {
	
public static String getLogin() {
	Authentication au=SecurityContextHolder.getContext().getAuthentication();
	if (au.isAuthenticated())
	{
	return ((User)au.getPrincipal()).getUsername();
	}
	return null;
}

public static User getUser() {
	Authentication au=SecurityContextHolder.getContext().getAuthentication();
	if (au.isAuthenticated())
	{
	return (User)au.getPrincipal();
	}
	return null;
}


public static boolean hasRole(String rn) {
	Authentication au=SecurityContextHolder.getContext().getAuthentication();
	if (au.isAuthenticated())
	{
		User u=(User)au.getPrincipal();
		for (GrantedAuthority ro: u.getAuthorities())
		{
			if (ro.getAuthority().equals(rn)) return true;
		}
	}
	return false;
}

public static String myRoles(String separator) {
	Authentication au=SecurityContextHolder.getContext().getAuthentication();
	String res="";
	if (au.isAuthenticated())
	{
		User u=(User)au.getPrincipal();
		for (GrantedAuthority ro: u.getAuthorities())
		{
			res+=ro.getAuthority()+separator;
		}
	}
	if (!res.isEmpty()) res=res.substring(0, res.length()-separator.length());
	return res;
}



}
