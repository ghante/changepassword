package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.persistence.AccountDao;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceDAOImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Transactional(readOnly = true)
    public Account getAccountIdByName(String userName) {
        return accountDao.getAccountByAccountName(userName);
    }

    @Transactional(readOnly = true)
    public Account get(Long account_id) {
        return accountDao.get(account_id);
    }
}
