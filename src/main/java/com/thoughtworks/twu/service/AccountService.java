package com.thoughtworks.twu.service;

import com.thoughtworks.twu.model.Account;

import java.util.List;

public interface AccountService {

    void save(Account account);

    List<Account> findAll();
}
