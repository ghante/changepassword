package integration.com.trailblazers.freewheelers.persistence;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.AccountRoleMapper;
import com.trailblazers.freewheelers.model.AccountRole;
import com.trailblazers.freewheelers.persistence.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AccountRoleMapperTest {

    private SqlSession sqlSession;
    private AccountRoleMapper accountRoleMapper;

    @Before
    public void setUp() {
        sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        accountRoleMapper = sqlSession.getMapper(AccountRoleMapper.class);
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.rollback();
        sqlSession.close();
    }

    @Test
    public void shouldInsertAnAccountRole() throws Exception {
        AccountRole accountRole = new AccountRole();
        accountRole.setAccount_name("Some Name");
        accountRole.setRole("Some Role");

        accountRoleMapper.insert(accountRole);

        assertThat(accountRole.getRole_id(), is(not(nullValue())));
    }

}
