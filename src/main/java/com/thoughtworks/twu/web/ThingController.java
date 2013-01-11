package com.thoughtworks.twu.web;

import com.thoughtworks.twu.service.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(ThingController.URL)
public class ThingController {

	static final String URL = "/thing";
	
	@Autowired
    ThingService thingService;

	@RequestMapping(method = RequestMethod.GET)
	public void get(Model model, @ModelAttribute ThingCommand thingCommand) {
		model.addAttribute("thingGrid", thingService.findAll());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(Model model, @Valid ThingCommand thingCommand, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("thingGrid", thingService.findAll());
			return URL;
		}
		thingService.save(thingCommand);
		return "redirect:" + URL;
	}
	
	@RequestMapping(method = RequestMethod.POST, params="_method=put")
	public String put(Model model, @Valid ThingGrid thingGrid, BindingResult result) {
		if (result.hasErrors()) {
			thingService.updateWithAll(thingGrid);
			return URL;
		}
		thingService.saveAll(thingGrid);
		return "redirect:" + URL;
	}
	
}
