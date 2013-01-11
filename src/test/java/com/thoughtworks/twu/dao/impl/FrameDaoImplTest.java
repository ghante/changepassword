package com.thoughtworks.twu.dao.impl;

import com.thoughtworks.twu.model.Frame;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Transactional
public class FrameDaoImplTest extends DaoTest {
	
	@Autowired
    FrameDaoImpl frameDaoImpl;
	
	@Test
	public void shouldSaveAllFields() {
        Frame frame = makeFrame();
		frameDaoImpl.save(frame);
		List<Frame> frames = frameDaoImpl.findAll();
		Assert.assertEquals("Name1", frames.get(0).getName());
		Assert.assertEquals(BigDecimal.valueOf(13.99), frames.get(0).getPrice());
		Assert.assertEquals("this frame is awesome", frames.get(0).getDescription());
		frameDaoImpl.delete(frame);
	}

    private Frame makeFrame() {
        Frame frame = new Frame();
        frame.setName("Name1");
        frame.setPrice(BigDecimal.valueOf(13.99));
        frame.setDescription("this frame is awesome");
        return frame;
    }

    //TODO: delete doesnt delete anything, session or transaction problems?
    @Ignore
    @Test
    public void shouldDeleteGivenFrame(){
        Frame frame = makeFrame();
        frameDaoImpl.save(frame);
        frameDaoImpl.delete(frame);
        assertThat(frameDaoImpl.findAll().size(), is(0));
    }
	
}
