package com.trailblazers.freewheelers.model;

import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

public class AccountValidation {

    public static List<String> verifyInputs(String email, String password, String name, String phoneNumber) {
        ArrayList<String> errors = new ArrayList<String>();

        if (!email.contains("@")) {
           errors.add("Must enter a valid email!");
        }

        if(password.isEmpty()) {
            errors.add("Must enter a password!");
        }

        if(name.isEmpty()) {
            errors.add("Must enter a name!");
        }

        if(phoneNumber.isEmpty()) {
            errors.add("Must enter a phone number!");
        }

        return errors;
    }

}