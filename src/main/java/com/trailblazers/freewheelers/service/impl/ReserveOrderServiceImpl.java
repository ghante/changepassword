package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.model.OrderStatus;
import com.trailblazers.freewheelers.persistence.ReserveOrderDao;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReserveOrderServiceImpl implements ReserveOrderService{

    @Autowired
    ReserveOrderDao reserveOrderDao;

    @Transactional
    public void save(ReserveOrder reserveOrder) {
        reserveOrderDao.save(reserveOrder);
    }

    @Transactional(readOnly = true)
    public List<ReserveOrder> findAllOrdersByAccountId(Long account_id) {
        return reserveOrderDao.getOrdersByAccountId(account_id);
    }

    @Transactional(readOnly = true)
    public List<ReserveOrder> getAllOrdersByAccount() {
        return reserveOrderDao.getAllOrdersByAccount();
    }

    @Override
    @Transactional
    public void updateOrderDetails(Long order_id, OrderStatus status, String note) {
        ReserveOrder order = reserveOrderDao.get(order_id);

        order.setStatus(status);
        order.setNote(note);

        reserveOrderDao.save(order);
    }

}
