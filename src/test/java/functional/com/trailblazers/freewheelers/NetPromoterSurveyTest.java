package functional.com.trailblazers.freewheelers;

import functional.com.trailblazers.freewheelers.helpers.FeedbackType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by rameshb on 1/16/14.
 */
public class NetPromoterSurveyTest extends UserJourneyBase {
    @Ignore
    public void testNetPromoterSurveyFlow() {
        // TODO: Create a SQL or something to remove the entries for NPS. To be done outside of this test.

        String adminUser = "NPSAdmin";
        String password = "password";
        String positiveUser = "PositiveUser";
        String negativeUser = "NegativeUser";
        String neutralUser = "NeutralUser";
        String frame = "NPS Frame";
        long quantity = 10;

        admin
                .there_is_an_admin(adminUser, password)
                .there_is_a_frame(frame, quantity)
                .there_is_a_user(positiveUser, password)
                .there_is_a_user(negativeUser, password)
                .there_is_a_user(neutralUser, password);

        // Positive Feedback
        user
                .logs_in_with(positiveUser, password)
                .reserves_item(frame);

        npsSurveyForm.submitFeedback(FeedbackType.Positive);
        Assert.assertTrue(npsSurveyForm.thankYouMessageExists());
        user.is_logged_out();

        // Neutral Feedback
        user
                .logs_in_with(neutralUser, password)
                .reserves_item(frame);

        npsSurveyForm.submitFeedback(FeedbackType.Neutral);
        user.is_logged_out();

        // Negative Feedback
        user
                .logs_in_with(negativeUser, password)
                .reserves_item(frame);

        npsSurveyForm.submitFeedback(FeedbackType.Negative);
        user.is_logged_out();

        // Admin visits the NPS page
        user
                .logs_in_with(adminUser, password)
                .visits_nps_page();

        // Verification
        Assert.assertTrue(npsReportPage.isTotalResponsesCorrect("3"));

        // TODO - Recheck the values based on NPS computation logic
        Assert.assertTrue(npsReportPage.isNPSScoreCorrect(""));
        Assert.assertTrue(npsReportPage.isPercentagePromotersCorrect(""));
        Assert.assertTrue(npsReportPage.isPercentagePassivesCorrect(""));
        Assert.assertTrue(npsReportPage.isPercentageDetractorsCorrect(""));

        // Admin logs out
        user.is_logged_out();
    }
}
