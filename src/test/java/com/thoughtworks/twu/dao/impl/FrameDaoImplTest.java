package com.thoughtworks.twu.dao.impl;

import com.thoughtworks.twu.model.*;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
public class FrameDaoImplTest extends DaoTest {
	
	@Autowired
    FrameDaoImpl frameDaoImpl;
	
	@Test
	public void all_fields_are_persisted() {
		Frame frame = new Frame();
		frame.setName("Name1");
		frame.setPrice(BigDecimal.valueOf(13.99));
		frame.setDescription("this frame is awesome");
		frameDaoImpl.save(frame);
		List<Frame> frames = frameDaoImpl.findAll();
		Assert.assertEquals("Name1", frames.get(0).getName());
		Assert.assertEquals(BigDecimal.valueOf(13.99), frames.get(0).getPrice());
		Assert.assertEquals("this frame is awesome", frames.get(0).getDescription());
		frameDaoImpl.delete(frame);
	}
	
}
