package functional.com.trailblazers.freewheelers;

import functional.com.trailblazers.freewheelers.helpers.FeedbackType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by rameshb on 1/16/14.
 */
public class NetPromoterScoreTest extends UserJourneyBase {

    private final String adminUser = "NPSAdmin";
    private final String password = "password";
    private final String promoter = "Promoter";
    private final String detractor1 = "DetractorOne";
    private final String detractor2 = "DetractorTwo";
    private final String detractor3 = "DetractorThree";
    private final String passiveUser = "PassiveUser";
    private final String frame = "NPS Frame";

    @Test
    @Ignore
    public void testNetPromoterSurveyFlow() {
        // TODO: Create a SQL or something to remove the entries for NPS. To be done outside of this test.

        long quantity = 10;

        admin
                .there_is_an_admin(adminUser, password)
                .there_is_a_frame(frame, quantity)
                .there_is_a_user(promoter, password)
                .there_is_a_user(passiveUser, password)
                .there_is_a_user(detractor1, password)
                .there_is_a_user(detractor2, password)
                .there_is_a_user(detractor3, password);

        // Positive Feedback
        user
                .logs_in_with(promoter, password)
                .reserves_item(frame);

        npsSurveyForm.submitFeedback(FeedbackType.Positive);
        Assert.assertTrue(npsSurveyForm.thankYouMessageExists());
        user.is_logged_out();

        // Neutral Feedback
        user
                .logs_in_with(passiveUser, password)
                .reserves_item(frame);

        npsSurveyForm.submitFeedback(FeedbackType.Neutral);
        user.is_logged_out();

        // Negative Feedback
        user
                .logs_in_with(detractor1, password)
                .reserves_item(frame);

        npsSurveyForm.submitFeedback(FeedbackType.Negative);
        user.is_logged_out();

        user
                .logs_in_with(detractor2, password)
                .reserves_item(frame);

        npsSurveyForm.submitFeedback(FeedbackType.Negative);
        user.is_logged_out();

        user
                .logs_in_with(detractor3, password)
                .reserves_item(frame);

        npsSurveyForm.submitFeedback(FeedbackType.Negative);
        user.is_logged_out();

        // Admin visits the NPS page
        user
                .logs_in_with(adminUser, password)
                .visits_nps_report_page();

        // Verification
        Assert.assertTrue(npsReportPage.totalResponsesCorrect("5"));

        // TODO - Recheck the values based on NPS computation logic
        // TODO - Confirm the total number of users. Currently it is assumed that the total users also include passive users
        Assert.assertTrue(npsReportPage.npsScoreCorrect("-40"));
        Assert.assertTrue(npsReportPage.percentagePromotersCorrect("20%"));
        Assert.assertTrue(npsReportPage.percentagePassivesCorrect("20%"));
        Assert.assertTrue(npsReportPage.percentageDetractorsCorrect("60%"));

        // Admin logs out
        user.is_logged_out();
    }

    @After
    public void tearDown() {
        admin
                .there_is_no_item(frame)
                .there_is_no_account_for(adminUser)
                .there_is_no_account_for(promoter)
                .there_is_no_account_for(passiveUser)
                .there_is_no_account_for(detractor1)
                .there_is_no_account_for(detractor2)
                .there_is_no_account_for(detractor3);
    }
}
