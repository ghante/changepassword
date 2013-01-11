package com.thoughtworks.twu.dao;

import com.thoughtworks.twu.model.*;

import java.util.List;

public interface ItemDao {

	Item get(Long id);
	void save(Item item);
	void delete(Item item);
	List<Item> findAll();

}
