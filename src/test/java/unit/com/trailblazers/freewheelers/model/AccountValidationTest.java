package unit.com.trailblazers.freewheelers.model;

import org.junit.Test;

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
        List<String> errors = verifyInputs(SOME_EMAIL, SOME_PASSWORD, SOME_NAME, SOME_PHONE);

        assertThat(errors.size(), is(0));
    }
    
    @Test
    public void shouldComplainAboutAnInvalidEmail() throws Exception {
        String invalidEmail = "invalid.email.address";

        List<String> errors = verifyInputs(invalidEmail, SOME_PASSWORD, SOME_NAME, SOME_PHONE);

        assertThereIsOneError("enter a valid email", errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyPassword() throws Exception {
        String emptyPassword = "";

        List<String> errors = verifyInputs(SOME_EMAIL, emptyPassword, SOME_NAME, SOME_PHONE);

        assertThereIsOneError("enter a password", errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyName() throws Exception {
        String emptyName = "";

        List<String> errors = verifyInputs(SOME_EMAIL, SOME_PASSWORD, emptyName, SOME_PHONE);

        assertThereIsOneError("enter a name", errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyPhoneNumber() throws Exception {
        String emptyPhoneNumber = "";

        List<String> errors = verifyInputs(SOME_EMAIL, SOME_PASSWORD, SOME_NAME, emptyPhoneNumber);

        assertThereIsOneError("enter a phone number", errors);
    }

    private void assertThereIsOneError(String substring, List<String> errors) {
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0), containsString(substring));
    }


}
