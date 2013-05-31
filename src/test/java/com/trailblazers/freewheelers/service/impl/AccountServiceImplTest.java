package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.AccountRoleMapper;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountRole;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ServiceResult;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountServiceImplTest {

    AccountService accountService;

    @Mock
    SqlSession sqlSession;
    @Mock
    AccountMapper accountMapper;
    @Mock
    AccountRoleMapper accountRoleMapper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(sqlSession.getMapper(AccountMapper.class)).thenReturn(accountMapper);
        when(sqlSession.getMapper(AccountRoleMapper.class)).thenReturn(accountRoleMapper);

        accountService = new AccountServiceImpl(sqlSession);
    }

    @Test
    public void shouldNotCreateAccountWhenThereAreValidationErrors(){
        Account account = mock(Account.class);
        HashMap errors = new HashMap();
        defineAccountWithErrors(account, errors);
        ServiceResult<Account> expectedServiceResult = new ServiceResult<Account>(errors, account);

        ServiceResult<Account> serviceResult = accountService.createAccount(account);

        verify(accountMapper, never()).insert(account);
        verify(accountRoleMapper, never()).insert(any(AccountRole.class));
        verify(sqlSession, never()).commit();
        assertServiceResult(serviceResult, expectedServiceResult);
    }

    @Test
    public void shouldCreateAccountWhenThereAreNoValidationErrors(){
        Account account = mock(Account.class);
        HashMap errors = new HashMap();
        defineAccountWithNoErrors(account);

        ServiceResult<Account> expectedServiceResult = new ServiceResult<Account>(errors, account);

        ServiceResult<Account> serviceResult = accountService.createAccount(account);

        verify(accountMapper, times(1)).insert(account);
        verify(accountRoleMapper, times(1)).insert(any(AccountRole.class));
        verify(sqlSession, times(1)).commit();
        assertServiceResult(serviceResult, expectedServiceResult);
    }

    private void defineAccountWithNoErrors(Account account) {
        when(account.getEmail_address()).thenReturn("example@example.com");
        when(account.getPassword()).thenReturn("example");
        when(account.getAccount_name()).thenReturn("Example Person");
        when(account.getPhoneNumber()).thenReturn("1234567890");
    }

    private void assertServiceResult(ServiceResult<Account> serviceResult, ServiceResult<Account> expectedServiceResult) {
        assertThat(serviceResult.getErrors(), is(expectedServiceResult.getErrors()));
        assertThat(serviceResult.getModel(), is(expectedServiceResult.getModel()));
    }

    private void defineAccountWithErrors(Account account, HashMap errors) {
        when(account.getEmail_address()).thenReturn("");
        errors.put("email", "Must enter a valid email!");

        when(account.getPassword()).thenReturn("");
        errors.put("password", "Must enter a password!");

        when(account.getAccount_name()).thenReturn("");
        errors.put("name", "Must enter a name!");

        when(account.getPhoneNumber()).thenReturn("");
        errors.put("phoneNumber", "Must enter a phone number!");
    }
}
