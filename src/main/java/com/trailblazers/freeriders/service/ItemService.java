package com.trailblazers.freeriders.service;

import com.trailblazers.freeriders.model.Item;
import com.trailblazers.freeriders.web.ItemCommand;
import com.trailblazers.freeriders.web.ItemGrid;

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
