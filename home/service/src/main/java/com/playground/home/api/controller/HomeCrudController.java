/**
 * 
 */
package com.playground.home.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.playground.home.api.model.ErrorResponseEntity;
import com.playground.home.api.model.HomeApiEntity;
import com.playground.home.api.model.HomeListApiEntity;
import com.playground.home.api.service.HomeService;
import com.playground.home.exceptions.EntityAlreadyExistsException;
import com.playground.home.exceptions.InvalidRequestException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author robert.burry
 *
 */
@Slf4j
@RestController
@RequestMapping(path = "/")
@CrossOrigin
public class HomeCrudController {

	@Autowired
	private HomeService homeService;

	@PostMapping(path = "home/")
	@SuppressWarnings("rawtypes")
	public ResponseEntity createHome(@RequestBody HomeApiEntity createRequest) {
		HomeApiEntity response;
		try {
			log.info("Createing Home from request: " + createRequest);
			response = homeService.create(createRequest);
			log.info("Createing Home Success");
			return new ResponseEntity<HomeApiEntity>(response, HttpStatus.CREATED);
		} catch (EntityAlreadyExistsException e) {
			// Should not have duplications
			return new ErrorResponseEntity(e.getMessage(), e.getErrorCode(), HttpStatus.CONFLICT);
		} catch (InvalidRequestException e) {
			return new ErrorResponseEntity(e.getMessage(), e.getErrorCode(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	@PostMapping(path = "homes")
	@GetMapping
	public ResponseEntity listHomes() {
		HomeListApiEntity response = homeService.getAll();
		return new ResponseEntity<HomeListApiEntity>(response, HttpStatus.OK);
	}

}
