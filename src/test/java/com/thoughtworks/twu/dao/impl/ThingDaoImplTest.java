package com.thoughtworks.twu.dao.impl;

import com.thoughtworks.twu.model.*;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ThingDaoImplTest extends DaoTest {
	
	@Autowired
    ThingDaoImpl thingDaoImpl;
	
	@Test
	public void all_fields_are_persisted() {
		Thing thing = new Thing();
		thing.setName("Name1");
		thing.setPrice("13.99");
		thing.setDescription("this thing is awesome");
		thingDaoImpl.save(thing);
		List<Thing> things = thingDaoImpl.findAll();
		Assert.assertEquals("Name1", things.get(0).getName());
		Assert.assertEquals("13.99", things.get(0).getPrice());
		Assert.assertEquals("this thing is awesome", things.get(0).getDescription());
		thingDaoImpl.delete(thing);
	}
	
}
