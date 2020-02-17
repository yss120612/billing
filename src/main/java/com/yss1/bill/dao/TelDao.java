package com.yss1.bill.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TelDao {
	@Autowired
	@Qualifier( "MySqlJdbcTemplate2")
	private JdbcTemplate jdbcTemplate2;
}