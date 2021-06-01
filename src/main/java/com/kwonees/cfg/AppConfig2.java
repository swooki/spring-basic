package com.kwonees.cfg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
public class AppConfig2 {
	
	@Value("${jdbc.driver}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.user}")
	private String user;
	@Value("${jdbc.password}")
	private String password;
	
	public AppConfig2() {
		System.out.println("AppConfig2 instantiated.");
	}
	@Bean
	public Connection connection() throws ClassNotFoundException, SQLException {
		Class.forName(driverClassName);
		return DriverManager.getConnection(url, user, password);
	}
	
	@Bean
	public JdbcProductDao jdbcDao(Connection connection) { 
//		JdbcProductDao dao = new JdbcProductDao();
//		dao.setConnection(connection);
//		return dao;
		return new JdbcProductDao();
	}
}
