package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.web.ItemCommand;
import com.trailblazers.freewheelers.web.ItemGrid;

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
