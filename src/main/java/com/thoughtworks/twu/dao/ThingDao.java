package com.thoughtworks.twu.dao;

import com.thoughtworks.twu.model.Thing;

import java.util.List;

public interface ThingDao {

	Thing get(Long id);
	void save(Thing thing);
	void delete(Thing thing);
	List<Thing> findAll();

}
