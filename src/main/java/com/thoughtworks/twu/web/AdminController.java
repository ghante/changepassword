package com.thoughtworks.twu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(AdminController.URL)
public class AdminController {

    static final String URL = "/admin";

    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public void get(Model model) {
    }

}
