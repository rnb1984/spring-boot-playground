/**
 * 
 */
package bdd.stepdefs.base;

import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

import bdd.config.TestConfig;
import cucumber.api.java.BeforeStep;

/**
 * @author robert.burry
 *
 */
//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
//@ContextConfiguration(classes = { HomeApplication.class, TestConfig.class }, loader = SpringBootContextLoader.class)
@ContextConfiguration(classes = { TestConfig.class }, loader = SpringBootContextLoader.class)
public class CucumberInit extends StepDefinitions {

	private boolean DB_EMPTY = false;

	@BeforeStep
	public void setUp() {
//		if (!DB_EMPTY) {
//			neo4jService.clearDatabase();
//		}
	}
}
