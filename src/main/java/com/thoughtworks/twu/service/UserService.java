package com.thoughtworks.twu.service;

import com.thoughtworks.twu.model.User;
import com.thoughtworks.twu.web.UserCommand;
import com.thoughtworks.twu.web.UserGrid;

public interface UserService {
	
	User get(Long id);
	
	void save(UserCommand userCommand);
	
	void delete(User user);
	
	UserGrid findAll();
	
	void saveAll(UserGrid userGrid);

	void updateWithAll(UserGrid userGrid);
	
}
