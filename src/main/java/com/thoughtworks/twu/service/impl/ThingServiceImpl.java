package com.thoughtworks.twu.service.impl;

import com.thoughtworks.twu.dao.ThingDao;
import com.thoughtworks.twu.model.Thing;
import com.thoughtworks.twu.service.ThingService;
import com.thoughtworks.twu.web.ThingCommand;
import com.thoughtworks.twu.web.ThingGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ThingServiceImpl implements ThingService {
	
	@Autowired
    ThingDao thingDao;
	
	@Transactional(readOnly = true)
	public Thing get(Long id) {
		return thingDao.get(id);
	}

	@Transactional
	public void delete(Thing thing) {
		thingDao.delete(thing);
	}

	@Transactional(readOnly = true)
	public ThingGrid findAll() {
		return new ThingGrid(thingDao.findAll());
	}

	@Transactional
	public void save(ThingCommand thingCommand) {
		thingDao.save(thingCommand.toThing());
	}

	@Transactional
	public void saveAll(ThingGrid thingGrid) {
		for (Thing thing : thingGrid.getThing())
			thingDao.save(thing);
	}
	
	@Transactional(readOnly = true)
	public void updateWithAll(ThingGrid thingGrid) {
		ThingGrid allThings = findAll();
		allThings.getThingMap().putAll(thingGrid.getThingMap());
		thingGrid.setThingMap(allThings.getThingMap());
	}
}
