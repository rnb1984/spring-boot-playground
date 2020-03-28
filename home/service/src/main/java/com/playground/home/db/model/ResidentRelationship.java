package com.playground.home.db.model;

import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RelationshipEntity(type = "RESIDENT")
public class ResidentRelationship extends Relationship<Home, User> {

	@Property
	private String description;

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
