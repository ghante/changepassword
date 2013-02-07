package integration.com.trailblazers.freeriders.persistence;

import com.trailblazers.freeriders.model.AccountRole;
import com.trailblazers.freeriders.persistence.AccountRoleDao;
import integration.com.trailblazers.freeriders.persistence.DaoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Transactional
public class AccountRoleDaoTest  extends DaoTest {

    @Autowired
    AccountRoleDao accountRoleDao;

    @Test
    public void shouldInsertRole(){
        AccountRole role = makeRole();
        accountRoleDao.save(role);
        List<AccountRole> roles = accountRoleDao.findAll();
        assertThat(roles.size(), is(3));
        assertThat(roles.get(2).getRole(), is("admin"));
        assertThat(roles.get(2).getAccount_name(), is("AdminCat"));
    }


    private AccountRole makeRole() {
        AccountRole role = new AccountRole();
        role.setRole("admin");
        role.setAccount_name("AdminCat");
        return role;
    }

}