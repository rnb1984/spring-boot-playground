/**
 * 
 */
package com.playground.home.db.model;

import org.apache.commons.lang3.StringUtils;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.Version;

import lombok.Getter;
import lombok.Setter;

/**
 * @author robert.burry
 *
 */

@Getter
@Setter
public class Relationship<S extends Identifiable, T extends Identifiable> {

	@Id
	@GeneratedValue
	private Long internalId;

	@Index(unique = true)
	private String id;

	@StartNode
	private S source;
	@EndNode
	private T target;

	@Version
	private Long version;

	private boolean lock;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null || source.getId() == null) ? 0 : source.getId().hashCode());
		result = prime * result + ((target == null || target.getId() == null) ? 0 : target.getId().hashCode());
		return result;
	}

	/*
	 * Check equality of another relationship to this object
	 * 
	 * @param Relationship
	 * 
	 * @return boolean
	 */

	@Override
	public boolean equals(Object obj) {
		// CHECK IF RELATIONSHIPS ARE THE SAME OBJECT
		if (this == obj)
			return true;
		// CHECK IF THE RELATIONSHIP IS NULL
		if (obj == null || this.getClass() != obj.getClass())
			return false;

		@SuppressWarnings("unchecked")
		Relationship<S, T> other = (Relationship<S, T>) obj;

		if (nodesEqual(source, other.source) && nodesEqual(target, other.target))
			// Check if source and target generic type match the source and target of this
			// relationship
			return other.getSource().getClass().equals(source.getClass())
					&& other.getTarget().getClass().equals(target.getClass());
		else
			return false;
	}

	private boolean nodesEqual(Identifiable left, Identifiable right) {
		if (left == null) {
			return right == null;
		} else {
			if (right == null) {
				return false;
			}
			return StringUtils.compare(left.getId(), right.getId()) == 0;
		}
	}
}
