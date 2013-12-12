package br.com.delogic.showcase.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericRepository<E extends LongEntityId> {

	private Map<Long, E> entities = new HashMap<Long, E>();
	{
		for (int i = 0; i < 50; i++) {
			E instance = create(i);
			entities.put(instance.getId(), instance);
		}
	}

	public abstract E create(int index);

	public E find(Long id) {
		return entities.get(id);
	}

	public List<E> findAll() {
		return new ArrayList<E>(entities.values());
	}

	public void save(E entity) {
		if (entity.getId() == null) {
			entity.setId(entities.size() + 1L);
		}
		entities.remove(entity.getId());
		entities.put(entity.getId(), entity);
	}

	public void remove(Long id) {
		entities.remove(id);
	}

}
