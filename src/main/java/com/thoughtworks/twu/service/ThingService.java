package com.thoughtworks.twu.service;

import com.thoughtworks.twu.model.Thing;
import com.thoughtworks.twu.web.ThingCommand;
import com.thoughtworks.twu.web.ThingGrid;

public interface ThingService {
	
	Thing get(Long id);
	
	void save(ThingCommand thingCommand);
	
	void delete(Thing thing);
	
	ThingGrid findAll();
	
	void saveAll(ThingGrid thingGrid);

	void updateWithAll(ThingGrid thingGrid);
	
}
