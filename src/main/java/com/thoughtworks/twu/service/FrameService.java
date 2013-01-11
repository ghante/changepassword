package com.thoughtworks.twu.service;

import com.thoughtworks.twu.model.Frame;
import com.thoughtworks.twu.web.FrameCommand;
import com.thoughtworks.twu.web.FrameGrid;

public interface FrameService {
	
	Frame get(Long id);
	
	void save(FrameCommand frameCommand);
	
	void delete(Frame frame);
	
	FrameGrid findAll();
	
	void saveAll(FrameGrid frameGrid);

	void updateWithAll(FrameGrid frameGrid);

    void deleteAll(FrameGrid frameGrid);
}
