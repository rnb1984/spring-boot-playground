package com.playground.home.app;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ConditionalOnProperty(name = "spring.data.neo4j.uri")
@EnableTransactionManagement
public class Neo4jConfiguration {

	@Value("${spring.data.neo4j.uri}")
	private String uri;
	@Value("${spring.data.neo4j.username}")
	private String username;
	@Value("${spring.data.neo4j.password}")
	private String password;
	@Value("${ogm.indexes.auto}")
	private String autoIndexes;

	@Bean
	public org.neo4j.ogm.config.Configuration configuration() {
		// @formatter:off
		return new org.neo4j.ogm.config.Configuration.Builder().autoIndex(autoIndexes).uri(uri)
				.credentials(username, password).build();
		// @formatter:on

	}

	@Bean
	public SessionFactory sessionFactory() {
		return new SessionFactory(configuration(), "com.playground.home.db.model");
	}

	@Bean
	public Neo4jTransactionManager transactionManager() {
		return new Neo4jTransactionManager(sessionFactory());
	}

}
