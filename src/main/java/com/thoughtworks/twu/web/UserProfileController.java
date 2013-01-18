package com.thoughtworks.twu.web;


import com.thoughtworks.twu.model.Account;
import com.thoughtworks.twu.model.Item;
import com.thoughtworks.twu.model.ReserveOrder;
import com.thoughtworks.twu.service.AccountService;
import com.thoughtworks.twu.service.ItemService;
import com.thoughtworks.twu.service.ReserveOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/userProfile")
public class UserProfileController {

    @Autowired
    AccountService accountService;

    @Autowired
    ReserveOrderService reserveOrderService;

    @Autowired
    ItemService itemService;

    @RequestMapping(value="/userProfile", method = RequestMethod.GET)
    public void get(Model model, Principal principal) {
        String userName = principal.getName();
        Account account =  accountService.getAccountIdByName(userName);

        List<Item> items = getItemsOrderByUser(account);

        ItemGrid itemGrid = new ItemGrid(items);
        model.addAttribute("itemGrid", itemGrid);

    }

    private List<Item> getItemsOrderByUser(Account account) {
        List<ReserveOrder> reserveOrders = reserveOrderService.findAllOrdersByAccountId(account.getAccount_id());
        List<Item> items = new ArrayList<Item>();
        for (ReserveOrder reserveOrder : reserveOrders){
            items.add(itemService.get(reserveOrder.getItem_id()));
        }
        return items;
    }


}
