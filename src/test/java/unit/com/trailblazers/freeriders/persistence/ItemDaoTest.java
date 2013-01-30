package unit.com.trailblazers.freeriders.persistence;

import com.trailblazers.freeriders.model.Item;
import com.trailblazers.freeriders.model.ItemType;
import com.trailblazers.freeriders.persistence.ItemDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@Transactional
public class ItemDaoTest extends DaoTest {
	
	@Autowired
    ItemDao itemDao;
    private final Long ONEQUANTITY = 1L;

    //TODO: can't test delete even after session flush :(
	
	@Test
	public void shouldSaveAllFields() {
        Item item = makeItem(ONEQUANTITY);
		itemDao.save(item);
		List<Item> items = itemDao.findAll();
		assertEquals("Frame1", items.get(0).getName());
		assertEquals(BigDecimal.valueOf(13.99), items.get(0).getPrice());
		assertEquals("this frame is awesome", items.get(0).getDescription());
		assertEquals(ItemType.TYPE.FRAME.toString(), items.get(0).getType());
        assertEquals(Long.valueOf(1), items.get(0).getQuantity());
	}

    @Test
    public void shouldGetItemById(){
        Item item = makeItem(ONEQUANTITY);
        Item insertedItem = itemDao.save(item);

        Item foundItem = itemDao.get(insertedItem.getItemId());
        assertThat(foundItem.getItemId(), is(insertedItem.getItemId()));
        assertEquals("Frame1", foundItem.getName());
        assertEquals(BigDecimal.valueOf(13.99), foundItem.getPrice());
        assertEquals("this frame is awesome", foundItem.getDescription());
        assertEquals(ItemType.TYPE.FRAME.toString(), foundItem.getType());
        assertEquals(Long.valueOf(1), foundItem.getQuantity());

    }

    @Test
    public void shouldReduceItemQuantityByOne(){
        Item item = makeItem(ONEQUANTITY);
        Item insertedItem = itemDao.save(item);

        itemDao.reduceQuantityByOne(insertedItem);
        Item foundItem = itemDao.get(insertedItem.getItemId());
        assertEquals("Frame1", foundItem.getName());
        assertEquals(BigDecimal.valueOf(13.99), foundItem.getPrice());
        assertEquals("this frame is awesome", foundItem.getDescription());
        assertEquals(ItemType.TYPE.FRAME.toString(), foundItem.getType());
        assertEquals(Long.valueOf(0), foundItem.getQuantity());
    }

    @Test
    public void shouldReturnOnlyItemsWithNonZeroQuantity(){

        Item item = makeItem(ONEQUANTITY);
        itemDao.save(item);

        item = makeItem(ONEQUANTITY - 1);
        itemDao.save(item);

        List<Item> itemsWithCountGreaterThanOne = itemDao.findItemWithNonZeroQuantity();
        List<Item> allItems = itemDao.findAll();
        assertEquals(1, itemsWithCountGreaterThanOne.size());
        assertEquals(2, allItems.size());
    }

    private Item makeItem(Long quantity) {
        Item item = new Item();
        item.setName("Frame1");
        item.setPrice(BigDecimal.valueOf(13.99));
        item.setDescription("this frame is awesome");
        item.setType(ItemType.TYPE.FRAME.toString());
        item.setQuantity(quantity);
        return item;
    }

}
