/**
 * 
 */
package com.playground.home.api.service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.playground.home.api.model.Home2ApiEntity;
import com.playground.home.api.model.HomeApiEntity;
import com.playground.home.api.model.HomeListApiEntity;
import com.playground.home.db.dao.HomeDAO;
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
	private HomeDAO homeDAO;

	@Autowired
	private TransactionalService tx;

	/**
	 * @param homeRequest
	 * @return HomeApiEntity
	 * @throws EntityAlreadyExistsException
	 * @throws InvalidRequestException
	 */
	@Transactional
	public HomeApiEntity create(HomeApiEntity homeRequest)
			throws EntityAlreadyExistsException, InvalidRequestException {
		Home home = new Home();

		validate(homeRequest);

		BeanUtils.copyProperties(homeRequest, home, "neighbours", "users");
		tx.insertHomeIntoDB(home);
		return buildResponse(home);
	}

	/**
	 * @param homeRequest
	 * @throws InvalidRequestException
	 */
	private void validate(HomeApiEntity homeRequest) throws InvalidRequestException {
		if (homeRequest == null) {
			throw new InvalidRequestException("Null Request", 101);
		}
	}

	/**
	 * @param home
	 * @return HomeApiEntity
	 */
	private HomeApiEntity buildResponse(Home home) {
		HomeApiEntity response = new HomeApiEntity();
		BeanUtils.copyProperties(home, response);
		return response;
	}

	/**
	 * @return HomeListApiEntity
	 */
	public HomeListApiEntity getAll() {
		try {
			return new HomeListApiEntity().homes(StreamSupport.stream(homeDAO.findAll().spliterator(), false)
					.map(this::buildResponse2).collect(Collectors.toList()));
		} catch (NullPointerException e) {
			return new HomeListApiEntity();
		}

	}

	/**
	 * @param home
	 * @return Home2ApiEntity
	 */
	private Home2ApiEntity buildResponse2(Home home) {
		Home2ApiEntity response = new Home2ApiEntity();
		BeanUtils.copyProperties(home, response);
		return response;
	}

}
