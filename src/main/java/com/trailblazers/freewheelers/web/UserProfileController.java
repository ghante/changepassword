package com.trailblazers.freewheelers.web;


import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/{userName:.*}", method = RequestMethod.GET)
    public String get(@PathVariable String userName, Model model, Principal principal) {
        if (userName == null) {
            userName = principal.getName();
        }

        Account account = accountService.getAccountIdByName(userName);

        List<Item> items = getItemsOrderByUser(account);

        ItemGrid itemGrid = new ItemGrid(items);
        model.addAttribute("itemGrid", itemGrid);
        model.addAttribute("userDetail", account);

        return "userProfile";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String get(Model model, Principal principal) {
        return get(null, model, principal);
    }

    private List<Item> getItemsOrderByUser(Account account) {
        List<ReserveOrder> reserveOrders = reserveOrderService.findAllOrdersByAccountId(account.getAccount_id());
        List<Item> items = new ArrayList<Item>();
        for (ReserveOrder reserveOrder : reserveOrders) {
            items.add(itemService.get(reserveOrder.getItem_id()));
        }
        return items;
    }


}
