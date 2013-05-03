package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountValidation;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView createAccountForm(Model model) {
        return new ModelAndView("account/create", "validationMessage", model);
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public ModelAndView processCreate(HttpServletRequest request) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");

        List<String> errors = AccountValidation.verifyInputs(email, password, name, phoneNumber);

        if (errors.size() > 0) {
            return showErrors(errors);
        }

        if (createAccount(email, password, name, phoneNumber)) {
            return showSuccess(name);
        } else {
            return showError();
        }

    }

    private ModelAndView showErrors(List<String> errors) {
        ModelMap model = new ModelMap();
        model.put("errors", errors);
        return new ModelAndView("account/create", "validationMessage", model);
    }

    private ModelAndView showError() {
        return new ModelAndView("account/createFailure");
    }

    private boolean createAccount(String email, String password, String name, String phoneNumber) throws IOException {
        Account account = new Account()
                .setEmail_address(email)
                .setPassword(password)
                .setAccount_name(name)
                .setPhoneNumber(phoneNumber)
                .setEnabled(true);

        AccountService accountService = new AccountServiceImpl();

        try {
            accountService.create(account);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private ModelAndView showSuccess(String name) {
        ModelMap model = new ModelMap();
        model.put("name", name);

        return new ModelAndView("account/createSuccess", "postedValues", model);
    }

}
