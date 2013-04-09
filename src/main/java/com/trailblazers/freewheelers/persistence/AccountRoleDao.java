package com.trailblazers.freewheelers.persistence;

import com.trailblazers.freewheelers.model.AccountRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void delete(AccountRole AccountRole) {
        sessionFactory.getCurrentSession().delete(AccountRole);
    }

    @SuppressWarnings("unchecked")
    public List<AccountRole> findAll() {
        return sessionFactory.getCurrentSession().createQuery(
                "FROM AccountRole " +
                        "ORDER BY role_id")
                .list();
    }

    public void save(AccountRole AccountRole) {
        sessionFactory.getCurrentSession().merge(AccountRole);

    }

}
