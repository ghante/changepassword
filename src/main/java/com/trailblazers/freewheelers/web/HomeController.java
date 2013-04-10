package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.impl.ItemServiceMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

    ItemService itemService = new ItemServiceMapperImpl();

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model, @ModelAttribute("itemCommand") ItemCommand itemCommand) {
        model.addAttribute("itemGrid", itemService.getItemsWithNonZeroQuantity());
        return "home";
	}

}

