/**
 * 
 */
package com.playground.home.api.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.playground.home.api.model.HomeApiEntity;
import com.playground.home.api.model.HomeListApiEntity;
import com.playground.home.api.service.HomeService;
import com.playground.home.exceptions.EntityAlreadyExistsException;
import com.playground.home.exceptions.InvalidRequestException;
import com.playground.home.util.TestUtil;

/**
 * @author robert.burry
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeCrudControllerTest {

	@InjectMocks
	private HomeCrudController injectedHomeController;

	@Mock
	private HomeService mockHomeService;

	private HomeApiEntity defaultHomeApiEntity;
	private HomeListApiEntity defaultHomeListApiEntity;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		defaultHomeApiEntity = TestUtil.createHomeApiPojo();
		defaultHomeListApiEntity = TestUtil.createListHomeApiPojo();
	}

	/**
	 * Test method for
	 * {@link com.playground.home.api.controller.HomeCrudController#createHome(com.playground.home.api.model.HomeApiEntity)}.
	 */
	@Test
	public void testCreateHome() throws Throwable {

		// '201':: home created
		when(mockHomeService.create(defaultHomeApiEntity)).thenReturn(defaultHomeApiEntity);
		assertNotNull(injectedHomeController);
		ResponseEntity response = injectedHomeController.createHome(defaultHomeApiEntity);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		// '409': conflict
		when(mockHomeService.create(defaultHomeApiEntity)).thenThrow(EntityAlreadyExistsException.class);
		response = injectedHomeController.createHome(defaultHomeApiEntity);
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

		// '422': required field missing or existing relationship
		when(mockHomeService.create(any())).thenThrow(InvalidRequestException.class);
		response = injectedHomeController.createHome(null);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());

		// '404': entity not found
//		response = injectedHomeController.createHome(null);
//		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

	}

	/**
	 * Test method for
	 * {@link com.playground.home.api.controller.HomeCrudController#listHomes()}.
	 */
	@Test
	public void testListHomes() {
		when(mockHomeService.getAll()).thenReturn(defaultHomeListApiEntity);
		ResponseEntity response = injectedHomeController.listHomes();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	public void testListHomesNull() {
		ResponseEntity response = injectedHomeController.listHomes();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNull(response.getBody());
	}

}
