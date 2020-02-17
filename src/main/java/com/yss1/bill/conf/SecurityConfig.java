package com.yss1.bill.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.yss1.bill.util.UserService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/fonts/**", "/img/**").permitAll()
				.antMatchers("/user*","/role*").hasRole("ADMIN")
				.anyRequest().fullyAuthenticated()
				.and()
				.formLogin().loginPage("/login").permitAll().failureUrl("/login?error=true").defaultSuccessUrl("/")
				.and()
				.logout().permitAll().logoutSuccessUrl("/login")
				.and()
				.exceptionHandling().accessDeniedPage("/forbidden");
	}

	
	
	
	@Autowired
	private UserService userService;

	 
	
	@Bean
    public PasswordEncoder passwordEncoder() {
       PasswordEncoder encoder = new BCryptPasswordEncoder();
       return encoder;
    }
	
	//@Autowired
	//private PasswordEncoder bpe;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder amb) throws Exception {
		amb.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

}
