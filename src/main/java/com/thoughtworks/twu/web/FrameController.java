package com.thoughtworks.twu.web;

import com.thoughtworks.twu.service.FrameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(FrameController.URL)
public class FrameController {

	static final String URL = "/frame";
	
	@Autowired
    FrameService frameService;

	@RequestMapping(method = RequestMethod.GET)
	public void get(Model model, @ModelAttribute FrameCommand frameCommand) {
		model.addAttribute("frameGrid", frameService.findAll());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(Model model, @Valid FrameCommand frameCommand, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("frameGrid", frameService.findAll());
			return URL;
		}
		frameService.save(frameCommand);
		return "redirect:" + URL;
	}
	
	@RequestMapping(method = RequestMethod.POST, params="_method=put")
	public String put(Model model, @Valid FrameGrid frameGrid, BindingResult result) {
		if (result.hasErrors()) {
			frameService.updateWithAll(frameGrid);
			return URL;
		}
		frameService.saveAll(frameGrid);
		return "redirect:" + URL;
	}
	
}
