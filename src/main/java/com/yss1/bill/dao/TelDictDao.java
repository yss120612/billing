package com.yss1.bill.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TelDictDao {
	@Autowired
	@Qualifier( "MySqlJdbcTemplate2")
	private JdbcTemplate jdbcTemplate2;
	
	
	public void ClearAll() {
		jdbcTemplate2.update("delete from owrtel");
	}
	
	public void AddTel(long number, String note) {
		jdbcTemplate2.update("insert into owrtel (tel,note) values(?,?)", number, note);
	}
	
	public void AddTel(long number) {
		AddTel(number,"");
	}

	public boolean isMyTel(long number) {
		int count= jdbcTemplate2.queryForObject("select count(*) from owrtel where tel=?",Integer.class,number);
		return count>0;
	}
	
	
}