package com.playground.home.exceptions;

import lombok.Getter;

/**
 * @author robert.burry
 *
 */
@Getter
public class InvalidRequestException extends Exception {

	private static final long serialVersionUID = 1L;
	private final int errorCode;

	public InvalidRequestException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}