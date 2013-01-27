package com.thoughtworks.university.service.impl;

import com.thoughtworks.university.dao.ItemDao;
import com.thoughtworks.university.model.Item;
import com.thoughtworks.university.service.ItemService;
import com.thoughtworks.university.web.ItemCommand;
import com.thoughtworks.university.web.ItemGrid;
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
    public ItemGrid getItemsWithNonZeroQuantity() {
        return new ItemGrid(itemDao.findItemWithNonZeroQuantity());
    }

    @Transactional
    public void decreaseQuantityByOne(Item item) {
        itemDao.reduceQuantityByOne(item);
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
    public void deleteItems(ItemGrid itemGrid) {
        for (Item item : itemGrid.getItem())
            delete(item);
    }
}
