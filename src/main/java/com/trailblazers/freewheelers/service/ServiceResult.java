package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.Account;

import java.util.Map;

public class ServiceResult {
    private Map<String, String> errors;
    private Account account;

    public ServiceResult(Map<String, String> errors, Account account) {

        this.errors = errors;
        this.account = account;
    }

    public Map<String,String> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public Account getAccount() {
        return account;
    }
}
