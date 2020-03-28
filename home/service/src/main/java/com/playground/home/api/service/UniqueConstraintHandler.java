/**
 * 
 */
package com.playground.home.api.service;

import com.playground.home.exceptions.EntityAlreadyExistsException;

/**
 * @author robert.burry
 *
 */
public interface UniqueConstraintHandler {
	void handleException(Exception e) throws EntityAlreadyExistsException;
}
