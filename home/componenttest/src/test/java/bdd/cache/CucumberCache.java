package bdd.cache;

import org.springframework.stereotype.Component;

/**
 * Caches a single item of type T
 * 
 * @author robert.burry
 *
 * @param <T>
 */
@Component
public class CucumberCache<T> {

	private T item;

	public T get() {
		return item;
	}

	public void put(T item) {
		this.item = item;
	}
}
