package com.thoughtworks.twu.service.impl;

import com.thoughtworks.twu.dao.ReserveOrderDao;
import com.thoughtworks.twu.model.ReserveOrder;
import com.thoughtworks.twu.service.ReserveOrderService;
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

}