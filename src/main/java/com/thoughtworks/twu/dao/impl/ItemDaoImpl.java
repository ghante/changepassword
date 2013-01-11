package com.thoughtworks.twu.dao.impl;

import com.thoughtworks.twu.dao.ItemDao;
import com.thoughtworks.twu.model.Item;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Item get(Long id) {
		return (Item) sessionFactory.getCurrentSession()
			.createQuery(
					"FROM Item u " +
					"WHERE u.id = :id " +
					"ORDER BY u.id")
			.setLong("id", id).uniqueResult();
	}

	public void delete(Item item) {
		sessionFactory.getCurrentSession().delete(item);
	}

	@SuppressWarnings("unchecked")
	public List<Item> findAll() {
		return sessionFactory.getCurrentSession().createQuery(
				"FROM Item " +
				"ORDER BY id")
			.list();
	}

	public void save(Item item) {
		sessionFactory.getCurrentSession().merge(item);

	}
	
}
