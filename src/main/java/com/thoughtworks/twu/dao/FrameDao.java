package com.thoughtworks.twu.dao;

import com.thoughtworks.twu.model.*;

import java.util.List;

public interface FrameDao {

	Frame get(Long id);
	void save(Frame frame);
	void delete(Frame frame);
	List<Frame> findAll();

}
