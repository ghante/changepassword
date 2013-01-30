package com.trailblazers.freeriders.service;

import com.trailblazers.freeriders.model.ReserveOrder;

import java.util.List;

public interface ReserveOrderService {

    void save(ReserveOrder reserveOrder);

    List<ReserveOrder> findAllOrdersByAccountId(Long account_id);

    List<ReserveOrder> getAllOrdersByAccount();
}
