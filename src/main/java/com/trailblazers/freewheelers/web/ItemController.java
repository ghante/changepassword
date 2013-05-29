package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ItemType;
import com.trailblazers.freewheelers.model.ItemValidation;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(ItemController.URL)
public class ItemController{

	static final String URL = "/item";
	static final String PAGE = "/itemList";

    ItemService itemService = new ItemServiceImpl();

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model, @ModelAttribute ItemCommand itemCommand) {
		model.addAttribute("itemGrid", itemService.findAll());
        model.addAttribute("itemTypes", ItemType.values());
        return PAGE;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(ModelMap model, ItemCommand itemCommand) {
        Item item = itemCommand.toItem();

        Map<String,String> errors = new ItemValidation().validate(item);

        if (!errors.isEmpty()) {
            model.put("errors", errors);
			model.addAttribute("itemGrid", itemService.findAll());
            model.addAttribute("itemTypes", ItemType.values());
			return PAGE;
		}
		itemService.save(item);
		return "redirect:" + URL;
	}
	
	@RequestMapping(method = RequestMethod.POST, params="update=Update all enabled items")
	public String updateItem(Model model, @Valid ItemGrid itemGrid, BindingResult result) {
		if (result.hasErrors()) {
			itemService.refreshItemList(itemGrid);
			return PAGE;
		}
		itemService.saveAll(itemGrid);
		return "redirect:" + URL;
	}

    @RequestMapping(method = RequestMethod.POST, params="delete=Delete all enabled items")
    public String deleteItem(Model model, @Valid ItemGrid itemGrid, BindingResult result) {
        if (result.hasErrors()) {
            itemService.refreshItemList(itemGrid);
            return PAGE;
        }
        itemService.deleteItems(itemGrid);
        return "redirect:" + URL;
    }
	
}
