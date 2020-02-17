package com.yss1.bill.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yss1.bill.models.Role;

@Repository
public class RoleDao {

	//@Autowired
	//@Qualifier( "MySqlDataSource2")
	//private DataSource pgDS;

	//private JdbcTemplate pgDT;
	
	//@PostConstruct
	//public void init() {
	//	pgDT=new JdbcTemplate(pgDS);
	//}
	
	@Autowired
	@Qualifier( "MySqlJdbcTemplate2")
	private JdbcTemplate jdbcTemplate2;

	
	public Role findRoleById(Long id) {
		Role n = jdbcTemplate2.queryForObject("select id,rolename from roles where id=" + id.toString(), nameRowMapper);
		return n;
	}

	public Role findRoleByName(String name) {
		Role n = null;
		try
		{
			n = jdbcTemplate2.queryForObject("select id,rolename from roles where rolename='" + name.trim().toUpperCase() + "'", nameRowMapper);
		}
		catch(Exception ex)
		{
			
		}
		return n;
	}

	public List<Role> getRoleList() {
		return jdbcTemplate2.query("select id,rolename from roles order by id", nameRowMapper);
	}

	public boolean deleteRole(long id) {
		Role r = findRoleById(id);
		return deleteRole(r);
	}

	public boolean deleteRole(String name) {
		Role r = findRoleByName(name);
		return deleteRole(r);
		
	}

	private boolean deleteRole(Role r) {
		if (r == null)
			return false;
		long id = r.getId();
		jdbcTemplate2.update("delete from users_roles where id_role=?", id);
		jdbcTemplate2.update("delete from roles where id=?", id);
		return true;
	}
	
	
	public Role addRole(String name) {
		if (!checkExist(name)) {
			jdbcTemplate2.update("insert into roles (rolename) values(?)", name.trim().toUpperCase());	
		}
		return findRoleByName(name);
	}

	public boolean editRole(Role r, String newName) {
		if (checkExist(newName)) {
			return false;
		}
		jdbcTemplate2.update("update roles set rolename=? where id=?", newName.trim().toUpperCase(), r.getId());
		return true;
	}

	public boolean checkExist(String name) {
		int l = jdbcTemplate2.queryForObject("select count(*) from roles where rolename='" + name.trim().toUpperCase() + "'", Integer.class);
		return l > 0;
	}

	private RowMapper<Role> nameRowMapper = new RowMapper<Role>() {
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role r = new Role();
			r.setRoleName(rs.getString("rolename"));
			r.setId(rs.getLong("id"));
			return r;
		}
	};
}
