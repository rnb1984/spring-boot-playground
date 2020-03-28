/**
 * 
 */
package com.playground.home.exceptions;

import lombok.Getter;

/**
 * @author robert.burry
 *
 */
@Getter
public class EntityAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;
	private final int errorCode;

	public EntityAlreadyExistsException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
