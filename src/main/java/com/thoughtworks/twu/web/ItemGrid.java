package com.thoughtworks.twu.web;

import com.thoughtworks.twu.model.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ItemGrid {
	
	@Valid
	private Map<Long, ItemCommand> itemMap;
	
	public ItemGrid() {
	}
	
	public ItemGrid(List<Item> items) {
		itemMap = new LinkedHashMap<Long, ItemCommand>();
		for (Item item : items)
			itemMap.put(item.getId(), new ItemCommand(item));
	}
	
	public List<Item> getItem() {
		List<Item> items = new ArrayList<Item>();
		for (ItemCommand itemCommand : itemMap.values()) {
			items.add(itemCommand.toItem());
		}
		return items;
	}

	public Map<Long, ItemCommand> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<Long, ItemCommand> items) {
		this.itemMap = items;
	}
	
}
