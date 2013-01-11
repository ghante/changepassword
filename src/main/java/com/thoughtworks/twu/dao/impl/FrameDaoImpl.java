package com.thoughtworks.twu.dao.impl;

import com.thoughtworks.twu.dao.FrameDao;
import com.thoughtworks.twu.model.Frame;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FrameDaoImpl implements FrameDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Frame get(Long id) {
		return (Frame) sessionFactory.getCurrentSession()
			.createQuery(
					"FROM Frame u " +
					"WHERE u.id = :id " +
					"ORDER BY u.id")
			.setLong("id", id).uniqueResult();
	}

	public void delete(Frame frame) {
		sessionFactory.getCurrentSession().delete(frame);
	}

	@SuppressWarnings("unchecked")
	public List<Frame> findAll() {
		return sessionFactory.getCurrentSession().createQuery(
				"FROM Frame " +
				"ORDER BY id")
			.list();
	}

	public void save(Frame frame) {
		sessionFactory.getCurrentSession().merge(frame);

	}
	
}
