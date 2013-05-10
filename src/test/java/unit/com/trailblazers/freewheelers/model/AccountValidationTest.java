package unit.com.trailblazers.freewheelers.model;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static com.trailblazers.freewheelers.model.AccountValidation.verifyInputs;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class AccountValidationTest {

    public static final String SOME_EMAIL = "guenter.grass@gmail.com";
    public static final String SOME_PASSWORD = "V3ry Secure!";
    public static final String SOME_NAME = "Günter Grass";
    public static final String SOME_PHONE = "004945542741";

    @Test
    public void shouldHaveNoErrorsForValidInput() throws Exception {
        HashMap errors = verifyInputs(SOME_EMAIL, SOME_PASSWORD, SOME_NAME, SOME_PHONE);

        assertThat(errors.size(), is(0));
    }
    
    @Test
    public void shouldComplainAboutAnInvalidEmail() throws Exception {
        String invalidEmail = "invalid.email.address";

        HashMap errors = verifyInputs(invalidEmail, SOME_PASSWORD, SOME_NAME, SOME_PHONE);

        assertThereIsOneErrorFor("email", "enter a valid email", errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyPassword() throws Exception {
        String emptyPassword = "";

        HashMap errors = verifyInputs(SOME_EMAIL, emptyPassword, SOME_NAME, SOME_PHONE);

        assertThereIsOneErrorFor("password", "enter a password", errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyName() throws Exception {
        String emptyName = "";

        HashMap errors = verifyInputs(SOME_EMAIL, SOME_PASSWORD, emptyName, SOME_PHONE);

        assertThereIsOneErrorFor("name", "enter a name", errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyPhoneNumber() throws Exception {
        String emptyPhoneNumber = "";

        HashMap errors = verifyInputs(SOME_EMAIL, SOME_PASSWORD, SOME_NAME, emptyPhoneNumber);

        assertThereIsOneErrorFor("phoneNumber", "enter a phone number", errors);
    }

    private void assertThereIsOneErrorFor(String field, String expected, HashMap<String, String> errors) {
        assertThat(errors.size(), is(1));
        assertThat(errors.get(field), containsString(expected));
    }


}
