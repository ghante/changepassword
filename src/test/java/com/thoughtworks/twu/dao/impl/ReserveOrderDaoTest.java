package com.thoughtworks.twu.dao.impl;

import com.thoughtworks.twu.dao.ReserveOrderDao;
import com.thoughtworks.twu.model.ReserveOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Transactional
public class ReserveOrderDaoTest extends DaoTest{

    @Autowired
    ReserveOrderDao orderDao;

    @Test
    public void shouldInsertOrder(){

        Date rightNow = new Date();

        ReserveOrder order = new ReserveOrder();
        order.setAccount_id(1L);
        order.setReservation_timestamp(rightNow);

        orderDao.save(order);

        List<ReserveOrder> orders = orderDao.findAll();
        assertThat(orders.size(), is(1));
        assertThat(orders.get(0).getAccount_id(), is(1L));
        assertThat(orders.get(0).getReservation_timestamp(), is(rightNow));

    }
}
