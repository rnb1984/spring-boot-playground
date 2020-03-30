package bdd.service;

import static io.restassured.RestAssured.given;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.restassured.specification.RequestSpecification;

@Service
public class Neo4jService {

	@Value("${neo4j.port:7474}")
	private String neo4jPort;

	/**
	 * 
	 */
	private RequestSpecification getNeo4jRequestSpecification() {
		return given().baseUri("http://localhost:" + neo4jPort);
	}

	/**
	 * Delete everything from the DB
	 */
	public void clearDatabase() {
		getNeo4jRequestSpecification()
				.header("Content-Type", "application/json")
				.body("{  \"query\" : \"MATCH (n) DETACH DELETE n\" }")
				.post("/db/data/cypher");
	}
}
