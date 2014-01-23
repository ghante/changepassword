package functional.com.trailblazers.freewheelers;

import org.junit.Ignore;
import org.junit.Test;

public class SurveyTest extends UserJourneyBase {

    private String userName = "user";
    private String password = "password";

    @Test
    @Ignore
    public void shouldSubmitSurveyAndGetThankYouMessage(){
        //Scenario 1: unlogged in user
        admin.there_is_a_user(userName, password);
        user.visit_survey_page();
        screen.should_show_login_page();

        //Scenario 2: logged in user
        user.logs_in_with(userName, password);
        screen.should_show_survey_page();

        //Filling in survey
        user.fills_in_survey(4, "no comment");
        screen.should_show_thank_you_page();
    }

}
