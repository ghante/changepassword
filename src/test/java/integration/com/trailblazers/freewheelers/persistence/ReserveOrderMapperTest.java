package integration.com.trailblazers.freewheelers.persistence;

import com.trailblazers.freewheelers.mappers.ReserveOrderMapper;
import com.trailblazers.freewheelers.model.ReserveOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ReserveOrderMapperTest extends MapperTestBase {

    private ReserveOrderMapper reserverOrderMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        reserverOrderMapper = getSqlSession().getMapper(ReserveOrderMapper.class);
    }

    private ReserveOrder someOrder() {
        return new ReserveOrder()
                .setAccount_id((long) 1)
                .setItem_id((long) 1)
                .setReservation_timestamp(new Date());
    }

    @Test
    public void shouldInsertAndGetAnOrder() throws Exception {
        ReserveOrder tobeInserted = someOrder();

        reserverOrderMapper.insert(tobeInserted);
        ReserveOrder fetched = reserverOrderMapper.get(tobeInserted.getOrder_id());

        assertThat(fetched, is(not(nullValue())));
    }

    @Test
    public void shouldDeleteAnOrder() throws Exception {
        ReserveOrder tobeDeleted = someOrder();
        reserverOrderMapper.insert(tobeDeleted);

        reserverOrderMapper.delete(tobeDeleted);

        ReserveOrder fetched = reserverOrderMapper.get(tobeDeleted.getOrder_id());
        assertThat(fetched, is(nullValue()));
    }

    @Test
    public void shouldUpdateAnOrder() throws Exception {
        ReserveOrder toBeUpdated = someOrder().setNote("");
        reserverOrderMapper.insert(toBeUpdated);

        toBeUpdated.setNote("A very important note.");
        reserverOrderMapper.save(toBeUpdated);

        ReserveOrder fetched = reserverOrderMapper.get(toBeUpdated.getOrder_id());
        assertThat(fetched.getNote(), is("A very important note."));
    }

    @Test
    public void shouldFindAllOrders() throws Exception {
        int before = reserverOrderMapper.findAll().size();
        reserverOrderMapper.insert(someOrder());

        List<ReserveOrder> all = reserverOrderMapper.findAll();

        assertThat(all.size(), is(before + 1));
    }

    @Test
    public void shouldFindAllOrdersForAnAccount() throws Exception {
        long someAccount = (long) 42;
        long anotherAccount = (long) 43;
        int before = reserverOrderMapper.findAllFor(someAccount).size();

        reserverOrderMapper.insert(someOrder().setAccount_id(someAccount));
        reserverOrderMapper.insert(someOrder().setAccount_id(someAccount));
        reserverOrderMapper.insert(someOrder().setAccount_id(anotherAccount));

        List<ReserveOrder> all = reserverOrderMapper.findAllFor(someAccount);

        assertThat(all.size(), is(before + 2));
    }

}
