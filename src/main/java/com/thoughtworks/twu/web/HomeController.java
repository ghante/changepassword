package com.thoughtworks.twu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(HomeController.URL)
public class HomeController {
	
	static final String URL = "/home";

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
        return URL;
	}
	
}

