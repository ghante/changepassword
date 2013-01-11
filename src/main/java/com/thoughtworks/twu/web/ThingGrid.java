package com.thoughtworks.twu.web;

import com.thoughtworks.twu.model.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ThingGrid {
	
	@Valid
	private Map<Long, ThingCommand> thingMap;
	
	public ThingGrid() {
	}
	
	public ThingGrid(List<Thing> things) {
		thingMap = new LinkedHashMap<Long, ThingCommand>();
		for (Thing thing : things)
			thingMap.put(thing.getId(), new ThingCommand(thing));
	}
	
	public List<Thing> getThing() {
		List<Thing> things = new ArrayList<Thing>();
		for (ThingCommand thingCommand : thingMap.values()) {
			things.add(thingCommand.toThing());
		}
		return things;
	}

	public Map<Long, ThingCommand> getThingMap() {
		return thingMap;
	}

	public void setThingMap(Map<Long, ThingCommand> things) {
		this.thingMap = things;
	}
	
}
