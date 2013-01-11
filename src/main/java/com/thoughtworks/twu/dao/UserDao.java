package com.thoughtworks.twu.dao;

import java.util.List;

import com.thoughtworks.twu.model.User;

public interface UserDao {

	User get(Long id);
	void save(User user);
	void delete(User user);
	List<User> findAll();

}
