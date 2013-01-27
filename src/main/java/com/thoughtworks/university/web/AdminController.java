package com.thoughtworks.university.web;

import com.thoughtworks.university.model.Account;
import com.thoughtworks.university.model.Item;
import com.thoughtworks.university.model.ReserveOrder;
import com.thoughtworks.university.service.AccountService;
import com.thoughtworks.university.service.ItemService;
import com.thoughtworks.university.service.ReserveOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(AdminController.URL)
public class AdminController {

    static final String URL = "/admin";

    @Autowired
    ReserveOrderService reserveOrderService;

    @Autowired
    ItemService itemService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public void get(Model model) {

        List<ReservedOrderDetail> reservedOrderDetails = getAllOrders();

        model.addAttribute("reserveOrders", reservedOrderDetails);

    }

    protected List<ReservedOrderDetail> getAllOrders() {
        List<ReserveOrder> reserveOrders = reserveOrderService.getAllOrdersByAccount();

        List<ReservedOrderDetail> reservedOrderDetails = new ArrayList<ReservedOrderDetail>();

        for (ReserveOrder reserveOrder: reserveOrders){
            Account account = accountService.get(reserveOrder.getAccount_id());
            Item item = itemService.get(reserveOrder.getItem_id());

            reservedOrderDetails.add(new ReservedOrderDetail(account, item, reserveOrder.getReservation_timestamp()));

        }
        return reservedOrderDetails;
    }

}