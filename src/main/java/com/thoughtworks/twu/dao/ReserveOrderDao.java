package com.thoughtworks.twu.dao;

import com.thoughtworks.twu.model.ReserveOrder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//postgres wont let me create table_name 'order'
@Repository
public class ReserveOrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    public ReserveOrder get(Long order_id) {
        return (ReserveOrder) sessionFactory.getCurrentSession()
                .createQuery(
                        "FROM ReserveOrder " +
                                "WHERE order_id = :order_id " +
                                "ORDER BY order_id")
                .setLong("order_id", order_id).uniqueResult();
    }

    public void delete(ReserveOrder reserveOrder) {
        sessionFactory.getCurrentSession().delete(reserveOrder);
    }

    @SuppressWarnings("unchecked")
    public List<ReserveOrder> findAll() {
        return sessionFactory.getCurrentSession().createQuery(
                "FROM ReserveOrder " +
                        "ORDER BY order_id")
                .list();
    }

    public void save(ReserveOrder reserveOrder) {
        sessionFactory.getCurrentSession().merge(reserveOrder);

    }

    public List<ReserveOrder> getOrdersByAccountId(Long account_id) {
        return sessionFactory.getCurrentSession().createQuery(
                "FROM ReserveOrder WHERE account_id = :account_id " +
                        "ORDER BY reservation_timestamp")
                .setLong("account_id", account_id).list();
    }
}
