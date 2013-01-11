package com.thoughtworks.twu.dao.impl;

import com.thoughtworks.twu.dao.ThingDao;
import com.thoughtworks.twu.model.Thing;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ThingDaoImpl implements ThingDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Thing get(Long id) {
		return (Thing) sessionFactory.getCurrentSession()
			.createQuery(
					"FROM Thing u " +
					"WHERE u.id = :id " +
					"ORDER BY u.id")
			.setLong("id", id).uniqueResult();
	}

	public void delete(Thing thing) {
		sessionFactory.getCurrentSession().delete(thing);
	}

	@SuppressWarnings("unchecked")
	public List<Thing> findAll() {
		return sessionFactory.getCurrentSession().createQuery(
				"FROM Thing " +
				"ORDER BY id")
			.list();
	}

	public void save(Thing thing) {
		sessionFactory.getCurrentSession().merge(thing);
		
	}
	
}
