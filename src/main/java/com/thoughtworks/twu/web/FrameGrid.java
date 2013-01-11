package com.thoughtworks.twu.web;

import com.thoughtworks.twu.model.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FrameGrid {
	
	@Valid
	private Map<Long, FrameCommand> frameMap;
	
	public FrameGrid() {
	}
	
	public FrameGrid(List<Frame> frames) {
		frameMap = new LinkedHashMap<Long, FrameCommand>();
		for (Frame frame : frames)
			frameMap.put(frame.getId(), new FrameCommand(frame));
	}
	
	public List<Frame> getFrame() {
		List<Frame> frames = new ArrayList<Frame>();
		for (FrameCommand frameCommand : frameMap.values()) {
			frames.add(frameCommand.toFrame());
		}
		return frames;
	}

	public Map<Long, FrameCommand> getFrameMap() {
		return frameMap;
	}

	public void setFrameMap(Map<Long, FrameCommand> frames) {
		this.frameMap = frames;
	}
	
}
