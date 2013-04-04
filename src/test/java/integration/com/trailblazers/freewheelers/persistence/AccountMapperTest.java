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
        Account toBeInserted = someAccount();
        toBeInserted.setAccount_name("Johnny Cash");

        accountMapper.insert(toBeInserted);
        sqlSession.commit();
        Account fetchedFromDB = accountMapper.getById(toBeInserted.getAccount_id());

        assertThat(fetchedFromDB.getAccount_name(), is("Johnny Cash"));
    }

    @Test
    public void shouldGetAccountByName() throws Exception {
        Account toBeInserted = someAccount();
        toBeInserted.setAccount_name("Michael Stipe");

        accountMapper.insert(toBeInserted);
        sqlSession.commit();
        Account fetchedFromDB = accountMapper.getByName("Michael Stipe");

        assertThat(fetchedFromDB.getAccount_name(), is("Michael Stipe"));
    }

    @Test
    public void shouldUpdateAnExistingAccount() throws Exception {
        Account someAccount = someAccount();
        someAccount.setAccount_name("Prince");

        accountMapper.insert(someAccount);
        sqlSession.commit();

        someAccount.setAccount_name("TAFKAP");
        accountMapper.update(someAccount);

        Account fetched = accountMapper.getById(someAccount.getAccount_id());

        assertThat(fetched.getAccount_name(), is("TAFKAP"));
    }

    private Account someAccount() {
        Account toBeInserted = new Account();
        toBeInserted.setAccount_name("Some Body");
        toBeInserted.setEmailAddress(UUID.randomUUID() + "some.body@gmail.com");
        toBeInserted.setPassword("V3ry S3cret");
        toBeInserted.setPhoneNumber("12345");
        toBeInserted.setEnabled(true);
        return toBeInserted;
    }

}

