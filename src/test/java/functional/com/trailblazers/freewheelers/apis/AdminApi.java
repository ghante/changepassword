package functional.com.trailblazers.freewheelers.apis;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;


public class AdminApi {

    public static final String SOME_PHONE_NUMBER = "555-123456";
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
                    .setEmail_address(emailFor(userName))
                    .setPhoneNumber(SOME_PHONE_NUMBER)
                    .setEnabled(true);
    }

    private String emailFor(String userName) {
        return userName.replace(' ', '-') + "@random-email.com";
    }
}
