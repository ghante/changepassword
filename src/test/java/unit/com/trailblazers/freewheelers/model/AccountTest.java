package unit.com.trailblazers.freewheelers.model;

import com.trailblazers.freewheelers.model.Account;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AccountTest {

    @Test
    public void testCreatingNewAccounts() throws Exception {
        String name = "Bob";
        String password = "password";
        String email = "foo@bar.com";
        String phoneNumber = "123443245";

        Account account = new Account();
        account.setAccount_name(name);
        account.setPassword(password);
        account.setEmailAddress(email);
        account.setPhoneNumber(phoneNumber);

        assertThat(account.getAccount_name(), is(name));
        assertThat(account.getPassword(), is(password));
        assertThat(account.getEmailAddress(), is(email));
        assertThat(account.getPhoneNumber(), is(phoneNumber));
    }
}
