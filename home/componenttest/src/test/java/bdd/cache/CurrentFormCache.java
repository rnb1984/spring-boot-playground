package bdd.cache;

import org.springframework.stereotype.Component;

@Component
public class CurrentFormCache<T> {

	private T item;

	public T get() {
		return item;
	}

	public void put(T item) {
		this.item = item;
	}
}
