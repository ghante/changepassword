package com.thoughtworks.twu.web;

import com.thoughtworks.twu.model.*;

public class FrameCommand extends Frame {

	private static final long serialVersionUID = -4686811929329857418L;

	private Boolean selected = false;
	
	public FrameCommand() {}

	public FrameCommand(Frame frame) {
		setId(frame.getId());
		setName(frame.getName());
		setPrice(frame.getPrice());
		setDescription(frame.getDescription());
	}

	public Frame toFrame() {
		Frame frame = new Frame();
		frame.setId(getId());
		frame.setName(getName());
		frame.setPrice(getPrice());
		frame.setDescription(getDescription());
		return frame;
	}
	
	public Boolean getSelected() {
		return selected;
	}
	
	public void setSelected(Boolean changed) {
		this.selected = changed;
	}
	
}