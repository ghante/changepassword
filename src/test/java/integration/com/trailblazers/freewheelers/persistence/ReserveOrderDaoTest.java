package integration.com.trailblazers.freewheelers.persistence;

import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.persistence.ReserveOrderDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Transactional
public class ReserveOrderDaoTest extends DaoTestBase {

    @Autowired
    ReserveOrderDao orderDao;
    private final Long USER_ID = 1L;
    private final Long ITEM_ID = 2L;

    @Test
    public void shouldInsertOrder(){

        Date reservationTime = reserveOrder(USER_ID, ITEM_ID);

        List<ReserveOrder> orders = orderDao.findAll();
        assertThat(orders.size(), is(1));
        assertThat(orders.get(0).getAccount_id(), is(USER_ID));
        assertThat(orders.get(0).getReservation_timestamp(), is(reservationTime));
        assertThat(orders.get(0).getItem_id(), is(ITEM_ID));

    }

    private Date reserveOrder(Long account_id, Long order_id) {
        Date rightNow = new Date();
        ReserveOrder order = new ReserveOrder(account_id, order_id, rightNow);
        orderDao.save(order);
        return rightNow;
    }

    @Test
    public void shouldFetchOrdersByAccountId(){

        reserveOrder(USER_ID, ITEM_ID);
        reserveOrder(USER_ID, ITEM_ID +1);
        reserveOrder(USER_ID, ITEM_ID +2);
        reserveOrder(2L, ITEM_ID +2);

        List<ReserveOrder> reserveOrders = orderDao.getOrdersByAccountId(USER_ID);
        assertThat(reserveOrders.get(0).getAccount_id(), is(USER_ID));
        assertThat(reserveOrders.get(1).getAccount_id(), is(USER_ID));
        assertThat(reserveOrders.get(2).getAccount_id(), is(USER_ID));

        assertThat(reserveOrders.get(0).getItem_id(), is(ITEM_ID));
        assertThat(reserveOrders.get(1).getItem_id(), is(ITEM_ID +1));
        assertThat(reserveOrders.get(2).getItem_id(), is(ITEM_ID +2));

        assertThat(reserveOrders.size(), is(3));

    }

    @Test
    public void shouldFetchAllOrdersGroupedByAccountId(){

        reserveOrder(USER_ID, ITEM_ID);
        reserveOrder(USER_ID, ITEM_ID +1);
        reserveOrder(USER_ID, ITEM_ID +2);
        reserveOrder(2L, ITEM_ID +2);

        List<ReserveOrder> reserveOrders = orderDao.getAllOrdersByAccount();
        assertThat(reserveOrders.get(0).getAccount_id(), is(USER_ID));
        assertThat(reserveOrders.get(1).getAccount_id(), is(USER_ID));
        assertThat(reserveOrders.get(2).getAccount_id(), is(USER_ID));
        assertThat(reserveOrders.get(3).getAccount_id(), is(2L));

        assertThat(reserveOrders.get(0).getItem_id(), is(ITEM_ID));
        assertThat(reserveOrders.get(1).getItem_id(), is(ITEM_ID +1));
        assertThat(reserveOrders.get(2).getItem_id(), is(ITEM_ID +2));
        assertThat(reserveOrders.get(3).getItem_id(), is(ITEM_ID +2));

        assertThat(reserveOrders.size(), is(4));

    }
}
