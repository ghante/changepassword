package com.trailblazers.freewheelers.model;

import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountValidation {

    public static HashMap verifyInputs(String email, String password, String name, String phoneNumber) {
        HashMap errors = new HashMap();

        if (!email.contains("@")) {
           errors.put("email", "Must enter a valid email!");
        }

        if(password.isEmpty()) {
            errors.put("password", "Must enter a password!");
        }

        if(name.isEmpty()) {
            errors.put("name", "Must enter a name!");
        }

        if(phoneNumber.isEmpty()) {
            errors.put("phoneNumber", "Must enter a phone number!");
        }

        return errors;
    }

}