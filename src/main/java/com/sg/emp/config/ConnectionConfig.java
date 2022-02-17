package com.sg.emp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ConnectionConfig {
	@Autowired
	Environment env;

	@Bean(destroyMethod = "close")
	@Qualifier("pgDataSource")
	public javax.sql.DataSource pgDataSource() {
		org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
		ds.setDriverClassName(env.getProperty("spring.database.driverClassName"));
		System.out.println(env.getProperty("spring.database.driverClassName"));
		ds.setUrl(env.getProperty("spring.datasource.url"));
		ds.setUsername(env.getProperty("spring.datasource.username"));
		ds.setPassword(env.getProperty("spring.datasource.password"));
		ds.setMaxWait(10000);
		ds.setTestOnBorrow(true);
		return ds;

	}

	@Bean()
	@Qualifier("pgJdbcTemplate")
	JdbcTemplate pgJdbcTemplate(@Qualifier("pgDataSource") DataSource pgDataSource) {
		return new JdbcTemplate(pgDataSource);
	}

}
