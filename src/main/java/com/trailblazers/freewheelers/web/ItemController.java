package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ItemType;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ServiceResult;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(ItemController.URL)
public class ItemController{

	static final String URL = "/item";
	static final String PAGE = "/itemList";

    ItemService itemService = new ItemServiceImpl();

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model, @ModelAttribute Item item) {
		model.addAttribute("itemGrid", itemService.findAll());
        model.addAttribute("itemTypes", ItemType.values());
        return PAGE;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(ModelMap model, @ModelAttribute Item item) {
        ServiceResult<Item> result = itemService.saveItem(item);

        if (result.hasErrors()) {
            model.put("errors", result.getErrors());
			model.addAttribute("itemGrid", itemService.findAll());
            model.addAttribute("itemTypes", ItemType.values());
			return PAGE;
		}
		return "redirect:" + URL;
	}

    @RequestMapping(method = RequestMethod.POST, params="update=Update all enabled items")
	public String updateItem(Model model, @ModelAttribute ItemGrid itemGrid) {
		itemService.saveAll(itemGrid);
		return "redirect:" + URL;
	}

    @RequestMapping(method = RequestMethod.POST, params="delete=Delete all enabled items")
    public String deleteItem(Model model, @ModelAttribute ItemGrid itemGrid) {
        itemService.deleteItems(itemGrid);
        return "redirect:" + URL;
    }
	
}
