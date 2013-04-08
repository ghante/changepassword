package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.persistence.MyBatisUtil;
import com.trailblazers.freewheelers.service.AccountService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    private SqlSession sqlSession;
    private AccountMapper accountMapper;

    public AccountServiceImpl() {
        this.sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        this.accountMapper = sqlSession.getMapper(AccountMapper.class);
    }

    @Override
    public List<Account> findAll() {
        return accountMapper.findAll();
    }

    @Override
    public Account getAccountIdByName(String userName) {
        return accountMapper.getByName(userName);
    }

    @Override
    public Account get(Long account_id) {
        return accountMapper.getById(account_id);
    }
}
