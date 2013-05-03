package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.AccountRoleMapper;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountRole;
import com.trailblazers.freewheelers.persistence.MyBatisUtil;
import com.trailblazers.freewheelers.service.AccountService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    public static final String USER = "ROLE_USER";
    private final AccountRoleMapper accountRoleMapper;
    private SqlSession sqlSession;
    private AccountMapper accountMapper;

    public AccountServiceImpl() {
        this.sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        this.accountMapper = sqlSession.getMapper(AccountMapper.class);
        this.accountRoleMapper = sqlSession.getMapper(AccountRoleMapper.class);
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

    @Override
    public void delete(Account account) {
        accountMapper.delete(account);
        sqlSession.commit();
    }

    @Override
    public void create(Account account) {
        accountMapper.insert(account);
        accountRoleMapper.insert(userRole(account));
        sqlSession.commit();
    }

    private AccountRole userRole(Account account) {
        return new AccountRole()
                .setAccount_name(account.getAccount_name())
                .setRole(USER);
    }
}
