package com.thoughtworks.twu.web;

import com.thoughtworks.twu.model.*;
import junit.framework.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ThingGridTest {
	
	@Test
	public void two_valid_things_should_not_have_violations() {

		List<Thing> things = new ArrayList<Thing>();

		things.add(new Thing());
		things.get(0).setName("name1");
		things.get(0).setPrice("13.99");
		things.get(0).setDescription("description1");

		things.add(new Thing());
		things.get(1).setName("name2");
		things.get(1).setPrice("14.99");
		things.get(1).setDescription("description2");

		ThingGrid thingGrid = new ThingGrid(things);
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<ThingGrid>> violations = validator.validate(thingGrid);
		Assert.assertTrue(violations.isEmpty());
	
	}

    @Test
    public void should_accept_valid_price() {

        List<Thing> things = new ArrayList<Thing>();

        things.add(new Thing());
        things.get(0).setName("name1");
        things.get(0).setPrice("13.99");
        things.get(0).setDescription("description1");

        ThingGrid thingsCommand = new ThingGrid(things);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ThingGrid>> violations = validator.validate(thingsCommand);
        Assert.assertTrue(violations.isEmpty());
    }

	@Test
	public void invalid_price_should_have_violations() {

		List<Thing> things = new ArrayList<Thing>();

		things.add(new Thing());
		things.get(0).setName("name1");
		things.get(0).setPrice("348324689");
		things.get(0).setDescription("description1");

		ThingGrid thingsCommand = new ThingGrid(things);
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<ThingGrid>> violations = validator.validate(thingsCommand);
		Assert.assertFalse(violations.isEmpty());
		for (ConstraintViolation<ThingGrid> violation : violations) {
			Assert.assertEquals("must be less than or equal to 99999", violation.getMessage());
		}
	
	}

    @Test
    public void price_should_accept_only_numbers() {

        List<Thing> things = new ArrayList<Thing>();
        things.add(new Thing());
        things.get(0).setName("name1");
        things.get(0).setPrice("amsbfd");
        things.get(0).setDescription("description1");

        ThingGrid thingsCommand = new ThingGrid(things);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ThingGrid>> violations = validator.validate(thingsCommand);
        Assert.assertFalse(violations.isEmpty());
        for (ConstraintViolation<ThingGrid> violation : violations) {
            Assert.assertEquals("must be less than or equal to 99999", violation.getMessage());
        }

    }

	
}
