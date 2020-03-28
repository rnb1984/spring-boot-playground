/**
 * 
 */
package com.playground.home.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.playground.home.api.model.HomeApiEntity;
import com.playground.home.exceptions.InvalidRequestException;
import com.playground.home.util.TestUtil;

/**
 * @author robert.burry-cic-uk@ibm.com
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeServiceTest {
	@InjectMocks
	private HomeService injectedHomeService;

	@Mock
	private TransactionalService mockTransactionalService;

	private HomeApiEntity defaultHomeApiEntity;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		defaultHomeApiEntity = TestUtil.createHomeApiPojo();
	}

	/**
	 * Test method for
	 * {@link com.playground.home.api.service.HomeService#create(com.playground.home.api.model.HomeApiEntity)}.
	 */
	@Test
	public void testCreate() throws Throwable {
//		when(mockTransactionalService.insertHomeIntoDB(any()));

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

}
