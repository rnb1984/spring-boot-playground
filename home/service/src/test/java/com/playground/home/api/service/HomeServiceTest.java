/**
 * 
 */
package com.playground.home.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.playground.home.api.model.Home2ApiEntity;
import com.playground.home.api.model.HomeApiEntity;
import com.playground.home.api.model.HomeListApiEntity;
import com.playground.home.db.dao.HomeDAO;
import com.playground.home.db.model.Home;
import com.playground.home.exceptions.InvalidRequestException;
import com.playground.home.util.TestUtil;

/**
 * @author robert.burry
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeServiceTest {
	@InjectMocks
	private HomeService injectedHomeService;

	@Mock
	private HomeDAO mockHomeDAO;

	@Mock
	private TransactionalService mockTransactionalService;

	private HomeApiEntity defaultHomeApiEntity;
	private Home defaultHome;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		defaultHomeApiEntity = TestUtil.createHomeApiPojo();
		defaultHome = TestUtil.createHomePojo();
	}

	/**
	 * Test method for
	 * {@link com.playground.home.api.service.HomeService#create(com.playground.home.api.model.HomeApiEntity)}.
	 */
	@Test
	public void testCreate() throws Throwable {

		HomeApiEntity actualHome = injectedHomeService.create(defaultHomeApiEntity);
		assertNotNull(actualHome);
		assertEquals(defaultHomeApiEntity.getId(), actualHome.getId());
		assertEquals(defaultHomeApiEntity.getName(), actualHome.getName());
		assertEquals(defaultHomeApiEntity.getInfo(), actualHome.getInfo());
		assertEquals(defaultHomeApiEntity.getUsers(), actualHome.getUsers());
		assertEquals(defaultHomeApiEntity.getNeighbours(), actualHome.getNeighbours());
	}

	@Test
	public void testCreateNull() throws Throwable {

		try {
			HomeApiEntity actualHome = injectedHomeService.create(null);
			fail("failed to throw InvalidRequestException");
		} catch (InvalidRequestException e) {
			assertEquals(101, e.getErrorCode());
			assertEquals("Null Request", e.getMessage());
		}
	}

	/**
	 * Test method for {@link com.playground.home.api.service.HomeService#getAll()}.
	 */
	@Test
	public void testGetAll() throws Throwable {
		when(mockHomeDAO.findAll()).thenReturn(Arrays.asList(defaultHome));

		HomeListApiEntity actualHomeApi = injectedHomeService.getAll();
		assertNotNull(actualHomeApi);

		List<Home2ApiEntity> actualHomes = actualHomeApi.getHomes();
		assertNotNull(actualHomes);
		assertFalse(actualHomes.isEmpty());

		Home2ApiEntity actualHome = actualHomes.get(0);
		assertNotNull(actualHome);
		assertEquals(defaultHome.getId(), actualHome.getId());
		assertEquals(defaultHome.getName(), actualHome.getName());
		assertEquals(defaultHome.getInfo(), actualHome.getInfo());
	}

	@Test
	public void testGetAllNull() throws Throwable {
		when(mockHomeDAO.findAll()).thenReturn(null);

		HomeListApiEntity actualHomeApi = injectedHomeService.getAll();
		assertNotNull(actualHomeApi);

		List<Home2ApiEntity> actualHomes = actualHomeApi.getHomes();
		assertNotNull(actualHomes);
		assertTrue(actualHomes.isEmpty());
	}

}
