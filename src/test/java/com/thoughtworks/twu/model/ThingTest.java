package com.thoughtworks.twu.model;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class ThingTest {
	
	@Test
	public void assert_that_certain_fields_cant_be_null_or_blank() {
		Thing thing = new Thing();
		Map<String, ConstraintViolation<Thing>> violationsMap = validate(thing);
		assertTrue(violationsMap.get("name").getMessageTemplate().contains("NotEmpty"));
		assertTrue(violationsMap.get("price").getMessageTemplate().contains("NotNull"));
		assertTrue(violationsMap.get("description").getMessageTemplate().contains("NotNull"));
	}
	
	private <T> Map<String, ConstraintViolation<T>>  validate(T thing) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Map<String, ConstraintViolation<T>> violations = new HashMap<String, ConstraintViolation<T>>();
		for (ConstraintViolation<T> violation : validator.validate(thing)) {
			violations.put(violation.getPropertyPath().toString(), violation);
		}
		return violations;
	}

}
