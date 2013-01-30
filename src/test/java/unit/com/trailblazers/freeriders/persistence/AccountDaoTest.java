package unit.com.trailblazers.freeriders.persistence;

import com.trailblazers.freeriders.model.Account;
import com.trailblazers.freeriders.persistence.AccountDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Transactional
public class AccountDaoTest extends DaoTest{

    @Autowired
    AccountDao accountDao;

    @Test
    public void shouldInsertAccount() {
        Account account = makeAccount();
        long id = accountDao.save(account).getAccount_id();
        Account retrievedAccount = accountDao.get(id);
        assertThat(retrievedAccount.getAccount_name(), equalTo(account.getAccount_name()));
    }

    @Test
    public void shouldGetAccountById(){
        Account account = makeAccount();
        Account insertedAccount = accountDao.save(account);

        Account foundAccount = accountDao.get(insertedAccount.getAccount_id());
        assertThat(foundAccount.getAccount_name(), is("octocat"));
        assertThat(foundAccount.getPassword(), is("meow"));
    }

    @Test
    public void shouldGetAccountByAccountName(){

        Account account = makeAccount();
        accountDao.save(account);

        Account foundAccount = accountDao.getAccountByAccountName(account.getAccount_name());
        assertThat(foundAccount.getAccount_name(), is("octocat"));
        assertThat(foundAccount.getPassword(), is("meow"));

    }

    private Account makeAccount() {
        Account account = new Account();
        account.setAccount_name("octocat");
        account.setPassword("meow");
        account.setEnabled(true);
        return account;
    }


}
