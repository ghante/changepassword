package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.persistence.DataAccess;
import com.trailblazers.freewheelers.persistence.DatabaseConnectionProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView createAccountForm(Model model) {
         return new ModelAndView("account/create", "validationMessage", model);
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public ModelAndView processCreate(HttpServletRequest request, ModelMap model) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");

        if (notValidInput(email, password, name, phoneNumber, address, model)) {
            return new ModelAndView("account/create", "validationMessage", model);
        }

        try {
            new DataAccess(new DatabaseConnectionProvider()).createAccount(email, password, name, phoneNumber, address);
        } catch (SQLException e) {
            model.put("name", e.toString());
        }

        model.put("name", name);
        return new ModelAndView("account/createResult", "postedValues", model);
    }

    private boolean notValidInput(String email, String password, String name, String phoneNumber, String address, ModelMap model) {
        boolean result = false;
        String message = "";

        if (!email.contains("@")) {
            result = true;
            message += "Must enter a valid email<br />";
        }

        if (password.isEmpty()) {
            result = true;
            message += "Must enter a password<br />";
        }

        if (name.isEmpty()) {
            result = true;
            message += "Must enter a name<br />";
        }

        if (phoneNumber.isEmpty()) {
            result = true;
            message += "Must enter a phone number<br />";
        }

        if (address.isEmpty()) {
            result = true;
            message += "Must enter an address<br />";
        }

        model.put("errors", message);
        return result;
    }

}
