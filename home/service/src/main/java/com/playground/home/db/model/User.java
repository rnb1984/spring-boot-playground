/**
 * 
 */
package com.playground.home.db.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author robert.burry
 *
 */

@Data
@NodeEntity
public class User implements Identifiable {

	/**
	 * Neo4j's internal Id This number can randomly change and must not be used for
	 * anything
	 */

	@Id
	@GeneratedValue
	private Long internalId;

	@NotNull
	@Index(unique = true)
	private String id;

	@EqualsAndHashCode.Exclude
	@Relationship(type = "RESIDENT", direction = Relationship.INCOMING)
	private Set<ResidentRelationship> residentOf = new HashSet<>();

}
