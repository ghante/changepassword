package com.trailblazers.freewheelers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Properties;

@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public String createAccountForm(Model model) {
         return "account/create";
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public ModelAndView processCreate(HttpServletRequest request, ModelMap model) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");


        model.put("name", name);

        try {
            Properties properties = new Properties();
            properties.setProperty("user", "postgres");
            properties.setProperty("password", "postgres");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/trailblazers", properties);

            String sql = "insert into account (email_address, account_name, password, enabled) ";
            sql +=       "values ('" + email + "', '" + name + "', '" + password + "', true)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            sql  = "insert into account_role (account_name, role) values ('" + name + "', 'ROLE_USER')";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            connection.close();

        } catch (SQLException e) {
            model.put("name", e.toString());
        }

        return new ModelAndView("account/createResult", "postedValues", model);
    }

}

