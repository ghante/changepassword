package com.thoughtworks.university.service;

import com.thoughtworks.university.model.ReserveOrder;

import java.util.List;

public interface ReserveOrderService {

    void save(ReserveOrder reserveOrder);

    List<ReserveOrder> findAllOrdersByAccountId(Long account_id);

    List<ReserveOrder> getAllOrdersByAccount();
}
