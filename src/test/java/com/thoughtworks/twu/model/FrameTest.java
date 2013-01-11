package com.thoughtworks.twu.model;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class FrameTest {
	
	@Test
	public void assert_that_certain_fields_cant_be_null_or_blank() {
		Frame frame = new Frame();
		Map<String, ConstraintViolation<Frame>> violationsMap = validate(frame);
		assertTrue(violationsMap.get("name").getMessageTemplate().contains("NotEmpty"));
		assertTrue(violationsMap.get("price").getMessageTemplate().contains("NotNull"));
		assertTrue(violationsMap.get("description").getMessageTemplate().contains("NotNull"));
	}
	
	private <T> Map<String, ConstraintViolation<T>>  validate(T frame) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Map<String, ConstraintViolation<T>> violations = new HashMap<String, ConstraintViolation<T>>();
		for (ConstraintViolation<T> violation : validator.validate(frame)) {
			violations.put(violation.getPropertyPath().toString(), violation);
		}
		return violations;
	}

}
