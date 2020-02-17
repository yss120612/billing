package com.yss1.bill.util;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yss1.bill.dao.RoleDao;
import com.yss1.bill.dao.UserDao;
import com.yss1.bill.models.Role;
import com.yss1.bill.models.User;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	
	@PostConstruct
	public void init() {
		//Если никого нет делаем юзера с 2я ролями
		if (userDao.getAllUsers().isEmpty()) {
			roleDao.addRole("ROLE_ADMIN");
			roleDao.addRole("ROLE_USER");
			User u =userDao.addUser("user", "1111",true, "ROLE_ADMIN,ROLE_USER");
		}
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getUserByName(username);
//		User user = new User();
//		user.setUsername(username);
//		user.setPassword("1111");
		if (user == null) {
			throw new UsernameNotFoundException("Пользователь " + username + " не найден!");
		}
		return user;
	}

}
