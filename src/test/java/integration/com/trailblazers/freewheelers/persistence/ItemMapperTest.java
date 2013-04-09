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
        Item item = new Item();
        item.setName("Some Item");
        item.setDescription("... with a very nice descrption");
        item.setPrice(valueOf(9.99));
        item.setQuantity(100L);
        item.setType("Some Type");

        itemMapper.insert(item);

        assertThat(item.getItemId(), is(not(nullValue())));
    }

}
