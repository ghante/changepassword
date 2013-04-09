package integration.com.trailblazers.freewheelers.persistence;

import com.trailblazers.freewheelers.mappers.ItemMapper;
import com.trailblazers.freewheelers.model.Item;
import org.junit.Before;
import org.junit.Test;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ItemMapperTest extends MapperTestBase {

    private ItemMapper itemMapper;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        itemMapper = getSqlSession().getMapper(ItemMapper.class);
    }

    @Test
    public void shouldInsertANewItem() throws Exception {
        Item item = someItem().setItemId(null);

        itemMapper.insert(item);

        assertThat(item.getItemId(), is(not(nullValue())));
    }

    @Test
    public void shouldFetchAnItemById() throws Exception {
        Item item = someItem().setName("Awesome Item");

        itemMapper.insert(item);
        Item fetched = itemMapper.get(item.getItemId());

        assertThat(fetched.getName(), is("Awesome Item"));
    }

    @Test
    public void shouldDeleteItem() throws Exception {
        Item item = someItem();
        itemMapper.insert(item);

        itemMapper.delete(item);
        Item fetched = itemMapper.get(item.getItemId());

        assertThat(fetched, is(nullValue()));
    }

    @Test
    public void shouldUpdateAnItem() throws Exception {
        Item item = someItem().setPrice(valueOf(100.00));
        itemMapper.insert(item);

        item.setPrice(valueOf(99.99));
        itemMapper.update(item);
        Item fetched = itemMapper.get(item.getItemId());

        assertThat(fetched.getPrice(), is(valueOf(99.99)));
    }

    private Item someItem() {
        return new Item()
                .setName("Some Item")
                .setDescription("... with a very nice descrption")
                .setPrice(valueOf(9.99))
                .setQuantity(100L)
                .setType("Some Type");
    }


}
