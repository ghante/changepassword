package com.thoughtworks.twu.service.impl;

import com.thoughtworks.twu.dao.AccountDao;
import com.thoughtworks.twu.model.Account;
import com.thoughtworks.twu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Transactional
    public void save(Account account) {
        accountDao.save(account);
    }

    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Transactional(readOnly = true)
    public Account getAccountIdByName(String userName) {
        return accountDao.getAccountByAccountName(userName);
    }
}
