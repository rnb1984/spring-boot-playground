/**
 * 
 */
package com.playground.home.db.dao;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.playground.home.db.model.Home;

/**
 * @author robert.burry
 *
 */
public interface HomeDAO extends Neo4jRepository<Home, Long> {

	public Home findById(String id);

	public Home findByName(String name);

}