package unit.com.trailblazers.freewheelers.model;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ItemValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ItemValidationTest {

    private ItemValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new ItemValidator();
    }

    private Item someItem() {
        return new Item()
                .setName("Some Item")
                .setDescription("... with a description")
                .setType("Some Type")
                .setPrice(valueOf(0.49))
                .setQuantity((long) 99);
    }

    @Test
    public void shouldSupportItemClass() throws Exception {
        assertThat(validator.supports(Item.class), is(true));
    }

    @Test
    public void shouldNotSupportOtherClasses() throws Exception {
        assertThat(validator.supports(BigDecimal.class), is(false));
    }

    @Test
    public void shouldHaveNoValidationErrorsForAValidItem() {
        assertThat(validate(someItem()).hasErrors(), is(false));
    }

    @Test
    public void shouldErrorWhenThereIsNoName() {
        assertFieldError(someItem().setName(""), "name", "Please enter Item Name");
    }

    @Test
    public void shouldErrorWhenThereIsNoPrice() {
        assertFieldError(someItem().setPrice(null), "price", "Please enter Item Price");
    }

    @Test
    public void shouldErrorWhenThereIsNoQuantity() {
        assertFieldError(someItem().setQuantity(null), "quantity", "Please enter Item Quantity");
    }

    @Test
    public void shouldErrorWhenThereIsNoType() {
        assertFieldError(someItem().setType(""), "type", "Please enter Item Type");
    }

    @Test
    public void shouldErrorWhenThereIsNoDescription() {
        assertFieldError(someItem().setDescription(""), "description", "Please enter Item Description");
    }

    @Test
    public void shouldHaveAPrice() {
        assertFieldError(someItem().setPrice(null), "price", "Please enter Item Price");
    }

    @Test
    public void should() throws Exception {
        Item ridiculouslyExpensive = someItem().setPrice(valueOf(100000.00));
        assertFieldError(ridiculouslyExpensive, "price", "must be less than or equal to 99999");
    }

    private BindingResult validate(Item item) {
        BindingResult result = new BeanPropertyBindingResult(item, "item");
        validator.validate(item, result);
        return result;
    }

    private void assertFieldError(Item item, String field, String expectedMessage) {
        FieldError error = validate(item).getFieldError(field);
        assertThat(error.getDefaultMessage(), is(expectedMessage));
    }



}
