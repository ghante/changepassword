package unit.com.trailblazers.freewheelers.model;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ItemValidation;
import org.junit.Test;

import java.util.List;

import static com.trailblazers.freewheelers.model.ItemValidation.validate;
import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ItemValidationTest {

    private Item someItem() {
        return new Item()
                .setName("Some Item")
                .setDescription("... with a description")
                .setType("Some Type")
                .setPrice(valueOf(0.49))
                .setQuantity((long) 99);
    }

    @Test
    public void shouldHaveNoValidationErrorsForAValidItem() throws Exception {
        assertThat(validate(someItem()).isEmpty(), is(true));
    }

    @Test
    public void shouldErrorWhenThereIsNoName() throws Exception {
        Item withoutAName = someItem().setName(null);
        assertThat(validate(withoutAName).get(0), is("Please enter Item Name"));
    }

    @Test
    public void shouldErrorWhenNameIsEmpty() throws Exception {
        Item withEmptyName = someItem().setName("");
        assertThat(validate(withEmptyName).get(0), is("Please enter Item Name"));
    }

    @Test
    public void shouldErrorWhenThereIsNoPrice() throws Exception {
        Item withoutAPrice = someItem().setPrice(null);
        assertThat(validate(withoutAPrice).get(0), is("Please enter Item Price"));
    }
    
    @Test
    public void shouldComplainAboutPricesThatAreWayTooHigh() throws Exception {
        Item ridiculouslyExpensive = someItem().setPrice(valueOf(100000.00));
        assertThat(validate(ridiculouslyExpensive).get(0), is("must be less than or equal to 99999"));
    }

    @Test
    public void shouldHaveADescriptionAtLeastAnEmptyOne() throws Exception {
        Item noDescription = someItem().setDescription(null);
        assertThat(validate(noDescription).get(0), is("Please enter Item Description"));
    }

    @Test
    public void shouldErrorWhenThereIsNoType() throws Exception {
        Item withoutAType = someItem().setType(null);
        assertThat(validate(withoutAType).get(0), is("Please select Item Type"));
    }

    @Test
    public void shouldErrorWhenTypeIsEmpty() throws Exception {
        Item withEmptyType = someItem().setType("");
        assertThat(validate(withEmptyType).get(0), is("Please select Item Type"));
    }

    @Test
    public void shouldErrorWhenThereIsNoQuantity() throws Exception {
        Item homeManyWeDontKnow = someItem().setQuantity(null);
        assertThat(validate(homeManyWeDontKnow).get(0), is("Please enter Item Quantity"));
    }

}
