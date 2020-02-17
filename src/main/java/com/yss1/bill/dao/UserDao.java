package com.yss1.bill.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.yss1.bill.models.Role;
import com.yss1.bill.models.User;

@Repository
public class UserDao {
//	@Autowired
//	@Qualifier( "MySqlDataSource2")
//	private DataSource pgDS;
//
//	private JdbcTemplate pgDT;
//	
	
	
//	@PostConstruct
//	public void init() {
//		pgDT=new JdbcTemplate(pgDS);
//	}
	
	@Autowired
	RoleDao roleDao;

	@Autowired
	private PasswordEncoder bpe;

	@Autowired
	//@Qualifier( "MySqlJdbcTemplate2")
	private JdbcTemplate jdbcTemplate2;
	

	private void fillRoles(User us) {
		List<Long> rl = jdbcTemplate2.query("select id_role from users_roles where id_user=" + us.getId(), idRowMapper);
		HashSet<Long> role_ids = (rl == null) ? new HashSet<Long>() : new HashSet<Long>(rl);
		HashSet<Role> roles = new HashSet<>();
		for (Long lx : role_ids) {
			roles.add(roleDao.findRoleById(lx));
		}
		us.setAuthorities(roles);
	}

	public User getUserByName(String name) {
		User u = null;
		try {
			u = jdbcTemplate2.queryForObject("select id,username,password,enable,locked from usr where username='" + name + "'",userRowMapper);
		} catch (Exception ex) {

		}
		if (u == null)
			return null;
		fillRoles(u);
		return u;
	}

	public List<User> getAllUsers() {
		List<User> ul = new ArrayList<User>(
				jdbcTemplate2.query("select id,username,password,enable,locked from usr", userRowMapper));
		for (User u : ul) {
			fillRoles(u);
		}
		return ul;
	}

	public User getUserById(long id) {
		User u = jdbcTemplate2.queryForObject("select id,username,password,enable,locked from usr where id=" + id,
				userRowMapper);
		if (u == null)
			return null;
		fillRoles(u);
		return u;
	}

	public boolean hasUser(String name) {
		Integer count = jdbcTemplate2.queryForObject("select count(*) from usr where username=?",Integer.class,name.trim());
		return count>0;
	}
	
	
	public User addUser(String name, String pass,boolean en,String roles) {
		if (hasUser(name)) return null;
		jdbcTemplate2.update("insert into usr (username,password,enable,locked) values(?,?,?,false)", name.trim(),bpe.encode(pass),en);
		User u=getUserByName(name);
		setRoles(u,roles);
//		List<Role> rl=roleDao.getRoleList();
//		for (String ro:roles) {
//			for (Role r:rl) {
//				if(r.getRoleName().equals(ro))
//				{
//					u.addRole(r);
//					break;
//				}
//			}
//		}
		updateRoles(u);
		return u; 
	}

	public String changePassword(String un, String oldpass, String newpass) {
		User u = getUserByName(un);
		if (u == null) {
			return "Пользователь " + un + " не найден!";
		}
		if (!bpe.matches(oldpass, u.getPassword())) {
			return "Неверный старый пароль!";
		}
		if (newpass.equals(oldpass)) {
			return "Пароли не совпадают!";
		}
		u.setPassword(bpe.encode(newpass));
		jdbcTemplate2.update("update usr set password=? where id=?", u.getPassword(), u.getId());
		return "";
	}

	public boolean saveUser(User u) {// если что то поменяли в ЮИ пишем в базу
		User u0 = getUserById(u.getId());
		if (u0 == null) {
			return false;
		}
		if (u0.isEnabled() != u.isEnabled() || u0.isAccountNonLocked() != u.isAccountNonLocked()) {// были поменяны
																									// флаги
			jdbcTemplate2.update("update usr set enable=?,locked=? where id=?", u.isEnabled(), u.isAccountNonLocked(),
					u.getId());
		}
		// update roles
		if (u.getAuthorities().size() == u0.getAuthorities().size()
				&& u.getAuthorities().containsAll(u0.getAuthorities())) {
			return true;// роли не трогали
		}
		updateRoles(u);
		return true;
	}

	public boolean deleteUser(long idx) {
		return deleteUser(getUserById(idx));
	}

	private void updateRoles(User u)
	{
		jdbcTemplate2.update("delete from users_roles where id_user=?", u.getId());
		for (Role r : (HashSet<Role>) u.getAuthorities()) {
			jdbcTemplate2.update("insert into users_roles (id_user, id_role) values(?,?)", u.getId(), r.getId());
		}
	}
	
	public void setRoles(User u,String rls) {
		Role r;
		u.clearRoles();
		if (rls!=null && !rls.isEmpty())
		{
		for (String s:rls.split(",")) {
			r=roleDao.findRoleByName(s);
			u.addRole(r);
		}
		}
	}
	
	public boolean deleteUser(String name) {
		return deleteUser(getUserByName(name));
	}

	private boolean deleteUser(User u) {
		if (u == null) {
			return false;
		}
		jdbcTemplate2.update("delete from users_roles where id_user=?", u.getId());
		jdbcTemplate2.update("delete from usr where id=?", u.getId());
		return true;
	}

	private RowMapper<User> userRowMapper = new RowMapper<User>() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setEnabled(rs.getBoolean("enable"));
			user.setLocked(rs.getBoolean("locked"));
			return user;
		}
	};

	private RowMapper<Long> idRowMapper = new RowMapper<Long>() {
		public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
			Long l = new Long(rs.getLong("id_role"));
			return l;
		}
	};

}
