package com.thoughtworks.twu.service;

import com.thoughtworks.twu.model.Item;
import com.thoughtworks.twu.web.ItemCommand;
import com.thoughtworks.twu.web.ItemGrid;

public interface ItemService {
	
	Item get(Long item_id);
	
	void save(ItemCommand itemCommand);
	
	void delete(Item item);
	
	ItemGrid findAll();
	
	void saveAll(ItemGrid itemGrid);

	void refreshItemList(ItemGrid itemGrid);

    void deleteAll(ItemGrid itemGrid);
}
