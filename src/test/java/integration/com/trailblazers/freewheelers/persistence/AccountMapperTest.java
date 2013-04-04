package integration.com.trailblazers.freewheelers.persistence;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.persistence.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AccountMapperTest {

    private SqlSession sqlSession;
    private AccountMapper accountMapper;

    @Before
    public void setUp() {
        sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        accountMapper = sqlSession.getMapper(AccountMapper.class);
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.close();
    }

    @Test
    public void shouldInsertAndGetAccount() throws Exception {
        Account account = someAccount().setAccount_name("Johnny Cash");

        accountMapper.insert(account);
        sqlSession.commit();
        Account fetchedFromDB = accountMapper.getById(account.getAccount_id());

        assertThat(fetchedFromDB.getAccount_name(), is("Johnny Cash"));
    }

    @Test
    public void shouldGetAccountByName() throws Exception {
        accountMapper.insert(someAccount().setAccount_name("Michael Stipe"));
        sqlSession.commit();

        Account fetchedFromDB = accountMapper.getByName("Michael Stipe");

        assertThat(fetchedFromDB.getAccount_name(), is("Michael Stipe"));
    }

    @Test
    public void shouldUpdateAnExistingAccount() throws Exception {
        Account someAccount = someAccount().setAccount_name("Prince");
        accountMapper.insert(someAccount);
        sqlSession.commit();

        someAccount.setAccount_name("TAFKAP");
        accountMapper.update(someAccount);

        Account fetched = accountMapper.getById(someAccount.getAccount_id());
        assertThat(fetched.getAccount_name(), is("TAFKAP"));
    }

    @Test
    public void shouldFindAllAccounts() throws Exception {
        int before = accountMapper.findAll().size();

        accountMapper.insert(someAccount());
        accountMapper.insert(someAccount());
        accountMapper.insert(someAccount());

        assertThat(accountMapper.findAll().size(), is(before + 3));
    }

    private Account someAccount() {
        return new Account()
                .setAccount_name("Some Body")
                .setEmailAddress(UUID.randomUUID() + "some.body@gmail.com")
                .setPassword("V3ry S3cret")
                .setPhoneNumber("12345")
                .setEnabled(true);
    }

}

