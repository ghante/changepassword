package com.thoughtworks.twu.web;

import com.thoughtworks.twu.service.FrameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

	static final String URL = "/home";

    @Autowired
    FrameService frameService;

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
        model.addAttribute("frameGrid", frameService.findAll());
        return URL;
	}

}

