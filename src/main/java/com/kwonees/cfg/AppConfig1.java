package com.kwonees.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.kwonees.dao.DummyProductDao;
import com.kwonees.dao.JdbcProductDao;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class AppConfig1 {
	
	@Value("${jdbc.driver}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.user}")
	private String user;
	@Value("${jdbc.password}")
	private String password;
	
	public AppConfig1() {
		System.out.println("AppConfig1 instantiated.");
	}
	@Lazy
	@Bean
	public DummyProductDao dummyDao() {
		System.out.println("dummyDao() called.");
		return new DummyProductDao();
	}
	
	@Lazy
	@Scope("singleton")  //{"singleton"|"prototype"}
	@Bean
	public JdbcProductDao jdbcDao() { 
		System.out.println("jdbcDao() called.");
		JdbcProductDao dao = new JdbcProductDao();
//		dao.setDriverClassName(driverClassName);
//		dao.setUrl(url);
//		dao.setUser(user);
//		dao.setPassword(password);
		return dao;
	}
}
