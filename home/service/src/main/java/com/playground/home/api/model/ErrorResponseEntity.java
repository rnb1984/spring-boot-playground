/**
 * 
 */
package com.playground.home.api.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author robert.burry
 *
 */
public class ErrorResponseEntity extends ResponseEntity<ErrorApiEntity> {
	public ErrorResponseEntity(String errorMessage, int errorCode, HttpStatus status) {
		super(new ErrorApiEntity().code(errorCode).message(errorMessage), status);
	}
}