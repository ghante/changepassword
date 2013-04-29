package functional.com.trailblazers.freewheelers.apis;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;


public class AdminApi {

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
        Account account = new Account()
                .setAccount_name(userName)
                .setPassword(password);

        // more stuff to do here :)
    }
}
