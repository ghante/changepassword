package com.thoughtworks.twu.dao;

import com.thoughtworks.twu.model.Item;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Item get(Long item_id) {
        return (Item) sessionFactory.getCurrentSession()
                .createQuery(
                        "FROM Item u " +
                                "WHERE u.item_id = :item_id " +
                                "ORDER BY u.item_id")
                .setLong("item_id", item_id).uniqueResult();
    }

    public void delete(Item item) {
        sessionFactory.getCurrentSession().delete(item);
    }

    @SuppressWarnings("unchecked")
    public List<Item> findAll() {
        return sessionFactory.getCurrentSession().createQuery(
                "FROM Item " +
                        "ORDER BY item_id")
                .list();
    }

    public void save(Item item) {
        sessionFactory.getCurrentSession().merge(item);

    }

}
