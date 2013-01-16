package com.thoughtworks.twu.service.impl;

import com.thoughtworks.twu.dao.ItemDao;
import com.thoughtworks.twu.model.Item;
import com.thoughtworks.twu.service.ItemService;
import com.thoughtworks.twu.web.ItemCommand;
import com.thoughtworks.twu.web.ItemGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
    ItemDao itemDao;
	
	@Transactional(readOnly = true)
	public Item get(Long item_id) {
		return itemDao.get(item_id);
	}

	@Transactional
	public void delete(Item item) {
		itemDao.delete(item);
	}

	@Transactional(readOnly = true)
	public ItemGrid findAll() {
		return new ItemGrid(itemDao.findAll());
	}

	@Transactional
	public void save(ItemCommand itemCommand) {
		itemDao.save(itemCommand.toItem());
	}

	@Transactional
	public void saveAll(ItemGrid itemGrid) {
		for (Item item : itemGrid.getItem())
			itemDao.save(item);
	}
	
	@Transactional(readOnly = true)
	public void refreshItemList(ItemGrid itemGrid) {
		ItemGrid allItems = findAll();
		allItems.getItemMap().putAll(itemGrid.getItemMap());
		itemGrid.setItemMap(allItems.getItemMap());
	}

    @Transactional
    public void deleteAll(ItemGrid itemGrid) {
        for (Item item : itemGrid.getItem())
            delete(item);
    }
}
