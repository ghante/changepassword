package com.trailblazers.freewheelers.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public String create() {
        return "account/create";
    }

}
