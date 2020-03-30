package bdd.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableNeo4jRepositories
//@ComponentScan(basePackages = { "bdd", "com.playground.home" }, basePackageClasses = { HomeApplication.class })
@ComponentScan(basePackages = { "bdd", "com.playground.home" })
public class TestConfig {

	public TestConfig() {
	}
}
