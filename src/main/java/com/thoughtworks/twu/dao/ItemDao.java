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

    //itemId=variable name item_id=postgres columnName
    public Item get(Long itemId) {
        return (Item) sessionFactory.getCurrentSession()
                .createQuery(
                        "FROM Item u " +
                                "WHERE u.itemId = :itemId " +
                                "ORDER BY u.itemId")
                .setLong("itemId", itemId).uniqueResult();
    }

    public void delete(Item item) {
        sessionFactory.getCurrentSession().delete(item);
    }

    @SuppressWarnings("unchecked")
    public List<Item> findAll() {
        return sessionFactory.getCurrentSession().createQuery(
                "FROM Item " +
                        "ORDER BY itemId")
                .list();
    }

    public Item save(Item item) {
        return  (Item) sessionFactory.getCurrentSession().merge(item);
    }

}
