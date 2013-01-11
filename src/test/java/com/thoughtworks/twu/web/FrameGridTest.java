package com.thoughtworks.twu.web;

import com.thoughtworks.twu.model.*;
import junit.framework.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FrameGridTest {
	
	@Test
	public void two_valid_frames_should_not_have_violations() {

		List<Frame> frames = new ArrayList<Frame>();

		frames.add(new Frame());
		frames.get(0).setName("name1");
		frames.get(0).setPrice(BigDecimal.valueOf(13.99));
		frames.get(0).setDescription("description1");

		frames.add(new Frame());
		frames.get(1).setName("name2");
		frames.get(1).setPrice(BigDecimal.valueOf(14.99));
		frames.get(1).setDescription("description2");

		FrameGrid frameGrid = new FrameGrid(frames);
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<FrameGrid>> violations = validator.validate(frameGrid);
		Assert.assertTrue(violations.isEmpty());
	
	}

    @Test
    public void should_accept_valid_price() {

        List<Frame> frames = new ArrayList<Frame>();

        frames.add(new Frame());
        frames.get(0).setName("name1");
        frames.get(0).setPrice(BigDecimal.valueOf(13.99));
        frames.get(0).setDescription("description1");

        FrameGrid framesCommand = new FrameGrid(frames);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<FrameGrid>> violations = validator.validate(framesCommand);
        Assert.assertTrue(violations.isEmpty());
    }

	@Test
	public void invalid_price_should_have_violations() {

		List<Frame> frames = new ArrayList<Frame>();

		frames.add(new Frame());
		frames.get(0).setName("name1");
		frames.get(0).setPrice(BigDecimal.valueOf(348324689));
		frames.get(0).setDescription("description1");

		FrameGrid framesCommand = new FrameGrid(frames);
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<FrameGrid>> violations = validator.validate(framesCommand);
		Assert.assertFalse(violations.isEmpty());
		for (ConstraintViolation<FrameGrid> violation : violations) {
			Assert.assertEquals("must be less than or equal to 99999", violation.getMessage());
		}
	
	}

}
