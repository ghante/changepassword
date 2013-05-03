package functional.com.trailblazers.freewheelers.apis;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;


public class AdminApi {

    public static final String SOME_EMAIL = "some@random.email";
    public static final String SOME_PHONE_NUMBER = "555-123456";
    private AccountServiceImpl accountService;

    public AdminApi() {
        this.accountService = new AccountServiceImpl();
    }

    public AdminApi there_is_no_user(String accountName) {
        Account account = accountService.getAccountIdByName(accountName);
        if (account != null) {
            accountService.delete(account);
        }

        return this;
    }

    public void there_is_a_user(String userName, String password) {
        there_is_no_user(userName);

        Account account = new Account()
                .setAccount_name(userName)
                .setPassword(password)
                .setEmail_address(SOME_EMAIL)
                .setPhoneNumber(SOME_PHONE_NUMBER)
                .setEnabled(true);

        accountService.create(account);
    }
}
