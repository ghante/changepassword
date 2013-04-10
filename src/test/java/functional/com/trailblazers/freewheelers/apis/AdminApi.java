package functional.com.trailblazers.freewheelers.apis;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;


public class AdminApi {

    public void there_is_no_user(String accountName) {
        AccountServiceImpl accountService = new AccountServiceImpl();

        Account account = accountService.getAccountIdByName(accountName);
        if (account != null) {
            accountService.delete(account);
        }
    }

}
