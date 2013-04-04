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
        Account toBeInserted = new Account();
        toBeInserted.setAccount_name("Some Body");
        toBeInserted.setEmailAddress(UUID.randomUUID() + "some.body@gmail.com");
        toBeInserted.setPassword("V3ry S3cret");
        toBeInserted.setPhoneNumber("12345");
        toBeInserted.setEnabled(true);

        accountMapper.insert(toBeInserted);
        sqlSession.commit();
        Account fetchedFromDB = accountMapper.getById(toBeInserted.getAccount_id());

        assertThat(fetchedFromDB.getAccount_name(), is("Some Body"));
    }

    @Test
    public void shouldGetAccountByName() throws Exception {
        Account toBeInserted = new Account();
        toBeInserted.setAccount_name("Some Body");
        toBeInserted.setEmailAddress(UUID.randomUUID() + "some.body@gmail.com");
        toBeInserted.setPassword("V3ry S3cret");
        toBeInserted.setPhoneNumber("12345");
        toBeInserted.setEnabled(true);

        accountMapper.insert(toBeInserted);
        sqlSession.commit();
        Account fetchedFromDB = accountMapper.getByName("Some Body");

        assertThat(fetchedFromDB.getAccount_name(), is("Some Body"));
    }

}

