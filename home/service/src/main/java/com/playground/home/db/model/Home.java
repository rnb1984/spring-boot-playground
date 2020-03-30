/**
 * 
 */
package com.playground.home.db.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author robert.burry
 *
 */

@Data
@NodeEntity
public class Home implements Identifiable {

	/**
	 * Neo4j's internal Id This number can randomly change and must not be used for
	 * anything
	 */

	@Id
	@GeneratedValue
	private Long internalId;

	@Version
	private Long version;

	@Index(unique = true)
	private String id;

	@NotNull(message = "Name can't be null")
	@NotBlank(message = "Name can't be blank")
	@Size(max = 120, message = "Name is too long")
	@Index(unique = true)
	private String name;

	@Size(max = 500, message = "Info is too long")
	private String info;

	private LocalDateTime createdTimestamp;

	@EqualsAndHashCode.Exclude
	@Relationship(type = "RESIDENT")
	private Set<ResidentRelationship> residentTo = new HashSet<>();

	@EqualsAndHashCode.Exclude
	@Relationship(type = "NEIGHBOUR", direction = Relationship.INCOMING)
	private Set<NeighbourRelationship> neighbourOf = new HashSet<>();

}
