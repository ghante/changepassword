package com.thoughtworks.twu.web;

import com.thoughtworks.twu.model.Thing;
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
		things.get(0).setEmail("name1@domain.net");
		things.get(0).setDescription("description1");

		things.add(new Thing());
		things.get(1).setName("name2");
		things.get(1).setEmail("name2@domain.net");
		things.get(1).setDescription("description2");

		ThingGrid thingGrid = new ThingGrid(things);
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<ThingGrid>> violations = validator.validate(thingGrid);
		Assert.assertTrue(violations.isEmpty());
	
	}

	@Test
	public void invalid_email_should_have_violations() {

		List<Thing> things = new ArrayList<Thing>();

		things.add(new Thing());
		things.get(0).setName("name1");
		things.get(0).setEmail("Invalid Email!!!!");
		things.get(0).setDescription("description1");

		ThingGrid thingsCommand = new ThingGrid(things);
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<ThingGrid>> violations = validator.validate(thingsCommand);
		Assert.assertFalse(violations.isEmpty());
		for (ConstraintViolation<ThingGrid> violation : violations) {
			Assert.assertEquals("not a well-formed email address", violation.getMessage());
		}
	
	}

	
}
