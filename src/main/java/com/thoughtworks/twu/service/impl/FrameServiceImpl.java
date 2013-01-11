package com.thoughtworks.twu.service.impl;

import com.thoughtworks.twu.dao.FrameDao;
import com.thoughtworks.twu.model.Frame;
import com.thoughtworks.twu.service.FrameService;
import com.thoughtworks.twu.web.FrameCommand;
import com.thoughtworks.twu.web.FrameGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FrameServiceImpl implements FrameService {
	
	@Autowired
    FrameDao frameDao;
	
	@Transactional(readOnly = true)
	public Frame get(Long id) {
		return frameDao.get(id);
	}

	@Transactional
	public void delete(Frame frame) {
		frameDao.delete(frame);
	}

	@Transactional(readOnly = true)
	public FrameGrid findAll() {
		return new FrameGrid(frameDao.findAll());
	}

	@Transactional
	public void save(FrameCommand frameCommand) {
		frameDao.save(frameCommand.toFrame());
	}

	@Transactional
	public void saveAll(FrameGrid frameGrid) {
		for (Frame frame : frameGrid.getFrame())
			frameDao.save(frame);
	}
	
	@Transactional(readOnly = true)
	public void updateWithAll(FrameGrid frameGrid) {
		FrameGrid allFrames = findAll();
		allFrames.getFrameMap().putAll(frameGrid.getFrameMap());
		frameGrid.setFrameMap(allFrames.getFrameMap());
	}
}
