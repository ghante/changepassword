package com.thoughtworks.twu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping(ReserveController.URL)
public class ReserveController {

    static final String URL = "/reserve";

    @RequestMapping(method = RequestMethod.GET)
    public void get(Model model, Principal principal) {

        final String currentUser = principal.getName();

        model.addAttribute("username", currentUser);
    }

}
