package com.thoughtworks.twu.web;

import com.thoughtworks.twu.model.Item;
import com.thoughtworks.twu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping(ReserveController.URL)
public class ReserveController {

    static final String URL = "/reserve";

    @Autowired
    ItemService itemService;

    @RequestMapping(method = RequestMethod.GET)
    public void get(Model model, Principal principal, @ModelAttribute("itemCommand") ItemCommand itemCommand) {

        final String currentUser = principal.getName();

        model.addAttribute("username", currentUser);
        model.addAttribute("itemId", itemCommand.getItemId());
    }

    @RequestMapping(method = RequestMethod.POST, params="reserve=Reserve Item")
    public void reserveItem(Model model, @ModelAttribute("itemCommand") ItemCommand itemCommand){
        Item item =  itemService.get(itemCommand.getItemId());
        model.addAttribute("item", item);
    }




}
