package com.thoughtworks.twu.dao.impl;

import com.thoughtworks.twu.dao.ItemDao;
import com.thoughtworks.twu.model.Item;
import com.thoughtworks.twu.model.ItemType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Transactional
public class ItemDaoTest extends DaoTest {
	
	@Autowired
    ItemDao itemDao;

    //TODO: can't test delete even after session flush :(
	
	@Test
	public void shouldSaveAllFields() {
        Item item = makeItem();
		itemDao.save(item);
		List<Item> items = itemDao.findAll();
		assertEquals("Frame1", items.get(0).getName());
		assertEquals(BigDecimal.valueOf(13.99), items.get(0).getPrice());
		assertEquals("this frame is awesome", items.get(0).getDescription());
		assertEquals(ItemType.TYPE.FRAME.toString(), items.get(0).getType());
	}

    @Test
    public void shouldGetItemById(){
        Item item = makeItem();
        Item insertedItem = itemDao.save(item);

        Item foundItem = itemDao.get(insertedItem.getItemId());
        assertEquals("Frame1", foundItem.getName());
        assertEquals(BigDecimal.valueOf(13.99), foundItem.getPrice());
        assertEquals("this frame is awesome", foundItem.getDescription());
        assertEquals(ItemType.TYPE.FRAME.toString(), foundItem.getType());

    }

    private Item makeItem() {
        Item item = new Item();
        item.setName("Frame1");
        item.setPrice(BigDecimal.valueOf(13.99));
        item.setDescription("this frame is awesome");
        item.setType(ItemType.TYPE.FRAME.toString());
        return item;
    }

}
