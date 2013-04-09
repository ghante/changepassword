package integration.com.trailblazers.freewheelers.persistence;

import com.trailblazers.freewheelers.mappers.ItemMapper;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.persistence.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ItemMapperTest {

    private SqlSession sqlSession;
    private ItemMapper itemMapper;

    @Before
    public void setUp() {
        sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        itemMapper = sqlSession.getMapper(ItemMapper.class);
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.rollback();
        sqlSession.close();
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

    private Item someItem() {
        return new Item()
                .setName("Some Item")
                .setDescription("... with a very nice descrption")
                .setPrice(valueOf(9.99))
                .setQuantity(100L)
                .setType("Some Type");
    }


}
