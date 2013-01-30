package com.trailblazers.freeriders.web;

import com.trailblazers.freeriders.model.Item;

public class ItemCommand extends Item {

	private Boolean selected = false;

	public ItemCommand() {}

	public ItemCommand(Item item) {
		setItemId(item.getItemId());
		setName(item.getName());
		setPrice(item.getPrice());
		setDescription(item.getDescription());
		setType(item.getType());
		setQuantity(item.getQuantity());
	}

	public Item toItem() {
		Item item = new Item();
		item.setItemId(getItemId());
		item.setName(getName());
		item.setPrice(getPrice());
		item.setDescription(getDescription());
		item.setType(getType());
		item.setQuantity(getQuantity());
		return item;
	}

	public Boolean getSelected() {
		return selected;
	}
	
	public void setSelected(Boolean changed) {
		this.selected = changed;
	}
	
}