package functional.com.trailblazers.freewheelers.pages;

import functional.com.trailblazers.freewheelers.core.PageHelper;
import functional.com.trailblazers.freewheelers.helpers.FeedbackType;
import org.openqa.selenium.By;

/**
 * Created by rameshb on 1/16/14.
 */
public class NPSSurveyForm extends PageHelper {
    private By positiveRatingRadio;
    private By neutralRatingRadio;
    private By negativeRatingRadio;
    private By commentsTextBox;
    private By submitButton;
    private By thankYouMessage;

    // TODO - Complete the element ids once NPS page is developed
    public NPSSurveyForm() {
        positiveRatingRadio = By.id("");
        neutralRatingRadio = By.id("");
        negativeRatingRadio = By.id("");
        commentsTextBox = By.id("");
        submitButton = By.id("");
        thankYouMessage = By.id("");
    }

    public void submitFeedback(FeedbackType feedbackType) {
        switch (feedbackType) {
            case Positive:
                findElement(positiveRatingRadio).click();
                break;
            case Neutral:
                findElement(neutralRatingRadio).click();
                break;
            case Negative:
                findElement(negativeRatingRadio).click();
                break;
        }

        findElement(commentsTextBox).sendKeys("Some Feedback");
        findElement(submitButton).click();
        waitForElement(thankYouMessage);
    }

    public boolean thankYouMessageExists() {
        return isElementExists(thankYouMessage);
    }
}
