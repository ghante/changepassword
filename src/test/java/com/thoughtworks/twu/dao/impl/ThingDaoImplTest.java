package com.thoughtworks.twu.dao.impl;

import com.thoughtworks.twu.model.Thing;
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
		thing.setEmail("simon@domain.com");
		thing.setDescription("31 My Street\nTown, TN 38103");
		thingDaoImpl.save(thing);
		List<Thing> things = thingDaoImpl.findAll();
		Assert.assertEquals("Name1", things.get(0).getName());
		Assert.assertEquals("simon@domain.com", things.get(0).getEmail());
		Assert.assertEquals("31 My Street\nTown, TN 38103", things.get(0).getDescription());
		thingDaoImpl.delete(thing);
	}
	
}
