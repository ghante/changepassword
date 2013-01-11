package com.thoughtworks.twu.dao.impl;

import com.thoughtworks.twu.model.Item;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
public class ItemDaoImplTest extends DaoTest {
	
	@Autowired
    ItemDaoImpl itemDaoImpl;
	
	@Test
	public void shouldSaveAllFields() {
        Item item = makeItem();
		itemDaoImpl.save(item);
		List<Item> items = itemDaoImpl.findAll();
		Assert.assertEquals("Name1", items.get(0).getName());
		Assert.assertEquals(BigDecimal.valueOf(13.99), items.get(0).getPrice());
		Assert.assertEquals("this item is awesome", items.get(0).getDescription());
		itemDaoImpl.delete(item);
	}

    private Item makeItem() {
        Item item = new Item();
        item.setName("Name1");
        item.setPrice(BigDecimal.valueOf(13.99));
        item.setDescription("this item is awesome");
        return item;
    }

}
