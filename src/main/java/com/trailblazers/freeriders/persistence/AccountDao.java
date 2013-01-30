package com.trailblazers.freeriders.persistence;

import com.trailblazers.freeriders.model.Account;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Account get(Long account_id) {
        return (Account) sessionFactory.getCurrentSession()
                .createQuery(
                        "FROM Account a " +
                                "WHERE a.account_id = :account_id " +
                                "ORDER BY a.account_id")
                .setLong("account_id", account_id).uniqueResult();
    }

    public void delete(Account account) {
        sessionFactory.getCurrentSession().delete(account);
    }

    @SuppressWarnings("unchecked")
    public List<Account> findAll() {
        return sessionFactory.getCurrentSession().createQuery(
                "FROM Account " +
                        "ORDER BY account_id")
                .list();
    }

    public Account save(Account account) {
        return (Account) sessionFactory.getCurrentSession().merge(account);
    }

    public Account getAccountByAccountName(String userName) {

        return (Account) sessionFactory.getCurrentSession()
                .createQuery(
                        "FROM Account " +
                                "WHERE account_name = :account_name")
                .setString("account_name", userName).uniqueResult();
    }
}
