package com.thoughtworks.twu.dao.impl;

import com.thoughtworks.twu.dao.AccountDao;
import com.thoughtworks.twu.model.Account;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Transactional
public class AccountDaoTest extends DaoTest{

    @Autowired
    AccountDao accountDao;

    @Test
    public void shouldInsertAccount(){
        Account account = makeAccount();
        accountDao.save(account);
        List<Account> accountList = accountDao.findAll();
        assertThat(accountList.size(), is(1));
        assertThat(accountList.get(0).getAccount_name(), is("octocat"));
        assertThat(accountList.get(0).getPassword(), is("meow"));
    }


    private Account makeAccount() {
        Account account = new Account();
        account.setAccount_name("octocat");
        account.setPassword("meow");
        account.setAccount_id(12L);
        account.setEnabled(1);
        return account;
    }


}
