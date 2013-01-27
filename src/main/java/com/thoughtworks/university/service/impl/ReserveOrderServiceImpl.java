package com.thoughtworks.university.service.impl;

import com.thoughtworks.university.dao.ReserveOrderDao;
import com.thoughtworks.university.model.ReserveOrder;
import com.thoughtworks.university.service.ReserveOrderService;
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

}
