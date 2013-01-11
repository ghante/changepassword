package com.thoughtworks.twu.web;

import com.thoughtworks.twu.model.*;

public class ThingCommand extends Thing {

	private static final long serialVersionUID = -4686811929329857418L;

	private Boolean selected = false;
	
	public ThingCommand() {}

	public ThingCommand(Thing thing) {
		setId(thing.getId());
		setName(thing.getName());
		setPrice(thing.getPrice());
		setDescription(thing.getDescription());
	}

	public Thing toThing() {
		Thing thing = new Thing();
		thing.setId(getId());
		thing.setName(getName());
		thing.setPrice(getPrice());
		thing.setDescription(getDescription());
		return thing;
	}
	
	public Boolean getSelected() {
		return selected;
	}
	
	public void setSelected(Boolean changed) {
		this.selected = changed;
	}
	
}