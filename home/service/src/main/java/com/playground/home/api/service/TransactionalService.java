/**
 * 
 */
package com.playground.home.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.playground.home.db.dao.HomeDAO;
import com.playground.home.db.model.Home;
import com.playground.home.exceptions.EntityAlreadyExistsException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author robert.burry
 *
 */
@Slf4j
@Service
public class TransactionalService {

	@Autowired
	private HomeDAO homeDao;

	@Autowired
	@Qualifier("nameIdUniqueConstrainthandler")
	UniqueConstraintHandler uniqueConstraintHandler;

	@Transactional
	public void insertHomeIntoDB(Home home) throws EntityAlreadyExistsException {

		try {
			homeDao.save(home);

			log.info("Home saved." + home.getName());

		} catch (Exception e) {
			uniqueConstraintHandler.handleException(e);

			throw new RuntimeException(e);
		}
	}

}
