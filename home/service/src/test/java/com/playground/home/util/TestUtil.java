/**
 * 
 */
package com.playground.home.util;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.playground.home.api.model.Home2ApiEntity;
import com.playground.home.api.model.HomeApiEntity;
import com.playground.home.api.model.HomeListApiEntity;
import com.playground.home.db.model.Home;

import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author robert.burry-cic-uk@ibm.com
 *
 */
@Service
public class TestUtil {

	// Factory for generating beans with random data
	private static PodamFactory beanFactory = new PodamFactoryImpl(new AbstractRandomDataProviderStrategy() {
		@Override
		public int getMaxDepth(Class<?> type) {
			return 1;
		}

		@Override
		public int getNumberOfCollectionElements(Class<?> type) {
			return 1;
		}
	});

	public static HomeApiEntity createHomeApiPojo() {
		return beanFactory.manufacturePojo(HomeApiEntity.class);
	}

	public static Home2ApiEntity createHome2ApiPojo() {
		return beanFactory.manufacturePojo(Home2ApiEntity.class);
	}

	public static HomeListApiEntity createListHomeApiPojo() {
		return new HomeListApiEntity().homes(Arrays.asList(createHome2ApiPojo()));
	}

	public static Home createHomePojo() {
		return beanFactory.manufacturePojo(Home.class);
	}

}
