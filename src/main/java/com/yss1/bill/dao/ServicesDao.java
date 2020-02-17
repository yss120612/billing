package com.yss1.bill.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yss1.bill.models.Role;
import com.yss1.bill.models.Service;
import com.yss1.bill.models.User;

@Repository
public class ServicesDao {
	@Autowired
	@Qualifier( "MySqlJdbcTemplate2")
	private JdbcTemplate jdbcTemplate2;
	
	private boolean checkID(int id) {
		int count= jdbcTemplate2.queryForObject("select count(*) from services where id=?",Integer.class,id);
		return count>0;
}
	
	public Service addService(int id, String name, short cons) {
		if (checkID(id)) return null;//already exists
		jdbcTemplate2.update("insert into services (id,name,const) values(?,?,?)", id, name.trim(),cons);
		return getService(id);
	}
	
	public Service getService(int id) {
		Service srv = jdbcTemplate2.queryForObject("select id,name,const from services where id=" + id , serviceRowMapper);
		return srv;
	}
	
	public Service updateNameService(int id, String newname) {
		if (checkID(id)) return null;//already exists
		jdbcTemplate2.update("update services set name=? where id=?", newname.trim(),id);
		return getService(id);
	}
	
	private RowMapper<Service> serviceRowMapper = new RowMapper<Service>() {
		public Service mapRow(ResultSet rs, int rowNum) throws SQLException {
			Service s = new Service();
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setConstant(rs.getShort("const"));
			return s;
		}
	};
	
}