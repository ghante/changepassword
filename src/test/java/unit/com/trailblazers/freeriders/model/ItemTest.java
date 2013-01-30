package unit.com.trailblazers.freeriders.model;

import com.trailblazers.freeriders.model.Item;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class ItemTest {
	
	@Test
	public void assert_that_certain_fields_cant_be_null_or_blank() {
		Item item = new Item();
		Map<String, ConstraintViolation<Item>> violationsMap = validate(item);
		assertTrue(violationsMap.get("name").getMessageTemplate().contains("Please enter Item Name"));
		assertTrue(violationsMap.get("price").getMessageTemplate().contains("NotNull"));
		assertTrue(violationsMap.get("description").getMessageTemplate().contains("NotNull"));
		assertTrue(violationsMap.get("type").getMessageTemplate().contains("Please select Item Type"));
	}
	
	private <T> Map<String, ConstraintViolation<T>>  validate(T item) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Map<String, ConstraintViolation<T>> violations = new HashMap<String, ConstraintViolation<T>>();
		for (ConstraintViolation<T> violation : validator.validate(item)) {
			violations.put(violation.getPropertyPath().toString(), violation);
		}
		return violations;
	}

}
