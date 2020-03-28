/**
 * 
 */
package com.playground.home.util;

import org.springframework.stereotype.Service;

import com.playground.home.api.model.HomeApiEntity;

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

}
