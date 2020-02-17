package com.yss1.bill.conf;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;



//коннекторы до баз данных
@Configuration
public class DbConfig {
	
	
//	@Autowired
//	private ApplicationContext ctx;
//	@PostConstruct 
//	private void init()
//	{
//		ApplicationContextUtil atu=new ApplicationContextUtil();
//		atu.setApplicationContext(ctx);
//	}
	
//	@Primary
//	@Bean(name = "postgressDS")
//	@ConfigurationProperties("app.datasource.postgressdb")
//	public DataSource dataSource1() {
//		DataSource ds= new DriverManagerDataSource();
//		return ds;
//	}
//	
//	@Bean(name = "as400DataSource")
//	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//	@Lazy(value = true)
//	@ConfigurationProperties("app.datasource.as400")
//	public DataSource dataSource2(){
//		DataSource dataSource = new SingleConnectionDataSource();
//        return dataSource;
//   }
	
	
	
	
	//@Bean(name = "MySqlDataSource2")
	//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	//@Lazy(value = true)
	
	private DataSource dataSource2(){
		DataSourceBuilder<?> dataSourceBuilder=DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://10.48.0.62/billing");
        dataSourceBuilder.username("user");
        dataSourceBuilder.password("1111");
		 return dataSourceBuilder.build();
	                
  }
	
	
	@Bean(name = "MySqlJdbcTemplate2")
	@Lazy(value = true)
	public JdbcTemplate jdbcTemplate2()
	{
		DataSourceBuilder<?> dataSourceBuilder=DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://10.48.0.62/billing");
        dataSourceBuilder.username("user");
        dataSourceBuilder.password("1111");
		return new JdbcTemplate(dataSourceBuilder.build());
	}
	
	
	@Bean(name = "MySqlDataSource1")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy(value = true)
	public DataSource dataSource1(){
		DataSourceBuilder<?> dataSourceBuilder=DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://10.48.0.216/www");
        dataSourceBuilder.username("user_report");
        dataSourceBuilder.password("1111");
		 return dataSourceBuilder.build();
	                
  }

	
	
	
	
	
//	@Bean(name = "postgressJdbcTemplate")
//	@Qualifier("postgressDS")
//	public JdbcTemplate postgressJdbcTemplate() {
//		DataSource ds = dataSource1();
//		return new JdbcTemplate(ds);
//	}
	
}
