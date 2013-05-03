package functional.com.trailblazers.freewheelers.apis;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import functional.com.trailblazers.freewheelers.helpers.SyntaxSugar;


public class AdminApi {

    private AccountServiceImpl accountService;

    public AdminApi() {
        this.accountService = new AccountServiceImpl();
    }

    public AdminApi there_is_no_account_for(String accountName) {
        Account account = accountService.getAccountIdByName(accountName);
        if (account != null) {
            accountService.delete(account);
        }

        return this;
    }

    public AdminApi there_is_a_user(String userName, String password) {
        there_is_no_account_for(userName);
        accountService.createUser(account_for(userName, password));

        return this;
    }

    public AdminApi there_is_an_admin(String userName, String password) {
        there_is_no_account_for(userName);
        accountService.createAdmin(account_for(userName, password));

        return this;
    }

    private Account account_for(String userName, String password) {
        return new Account()
                    .setAccount_name(userName)
                    .setPassword(password)
                    .setEmail_address(SyntaxSugar.emailFor(userName))
                    .setPhoneNumber(SyntaxSugar.SOME_PHONE_NUMBER)
                    .setEnabled(true);
    }

}
