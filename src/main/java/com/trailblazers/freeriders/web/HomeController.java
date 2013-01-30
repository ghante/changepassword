package com.trailblazers.freeriders.web;

import com.trailblazers.freeriders.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    ItemService itemService;

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model, @ModelAttribute("itemCommand") ItemCommand itemCommand) {
        model.addAttribute("itemGrid", itemService.getItemsWithNonZeroQuantity());
        return "home";
	}

}

