/**
 * 
 */
package com.playground.home.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playground.home.api.model.HomeApiEntity;
import com.playground.home.db.model.Home;
import com.playground.home.exceptions.EntityAlreadyExistsException;
import com.playground.home.exceptions.InvalidRequestException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author robert.burry
 *
 */
@Slf4j
@Service
public class HomeService {

	@Autowired
	private TransactionalService tx;

	public HomeApiEntity create(HomeApiEntity homeRequest)
			throws EntityAlreadyExistsException, InvalidRequestException {
		Home home = new Home();

		if (homeRequest == null) {
			throw new InvalidRequestException("Null Request", 101);
		}

		BeanUtils.copyProperties(homeRequest, home);
		tx.insertHomeIntoDB(home);
		return bulidResponse(home);
	}

	private HomeApiEntity bulidResponse(Home home) {
		HomeApiEntity response = new HomeApiEntity();
		BeanUtils.copyProperties(home, response);
		return response;
	}

}
