package com.thoughtworks.university.service;

import com.thoughtworks.university.model.Item;
import com.thoughtworks.university.web.ItemCommand;
import com.thoughtworks.university.web.ItemGrid;

public interface ItemService {
	
	Item get(Long item_id);
	
	void save(ItemCommand itemCommand);
	
	void delete(Item item);
	
	ItemGrid findAll();

    ItemGrid getItemsWithNonZeroQuantity();
	
	void saveAll(ItemGrid itemGrid);

	void refreshItemList(ItemGrid itemGrid);

    void deleteItems(ItemGrid itemGrid);

    void decreaseQuantityByOne(Item item);
}
