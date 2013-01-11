package com.thoughtworks.twu.web;

import com.thoughtworks.twu.model.*;

public class ItemCommand extends Item {

	private static final long serialVersionUID = -4686811929329857418L;

	private Boolean selected = false;
	
	public ItemCommand() {}

	public ItemCommand(Item item) {
		setId(item.getId());
		setName(item.getName());
		setPrice(item.getPrice());
		setDescription(item.getDescription());
	}

	public Item toItem() {
		Item item = new Item();
		item.setId(getId());
		item.setName(getName());
		item.setPrice(getPrice());
		item.setDescription(getDescription());
		return item;
	}
	
	public Boolean getSelected() {
		return selected;
	}
	
	public void setSelected(Boolean changed) {
		this.selected = changed;
	}
	
}