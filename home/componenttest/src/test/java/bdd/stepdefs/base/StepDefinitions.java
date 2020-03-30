/**
 * 
 */
package bdd.stepdefs.base;

import org.springframework.beans.factory.annotation.Autowired;

import bdd.service.ApiService;
import bdd.service.Neo4jService;

/**
 * @author robert.burry
 * 
 *         This is the super user
 *
 */
public class StepDefinitions {

	@Autowired
	protected CucucmberSession session;

	@Autowired
	protected Neo4jService neo4jService;

	@Autowired
	protected ApiService apiService;

}
