package functional.com.trailblazers.freewheelers.pages;

import functional.com.trailblazers.freewheelers.core.PageHelper;
import org.openqa.selenium.By;

/**
 * Created by rameshb on 1/16/14.
 */
public class NPSReportPage extends PageHelper {
    private By totalResponses;
    private By npsScore;
    private By percentagePromoters;
    private By percentagePassives;
    private By percentageDetractors;

    // TODO - Complete the element ids once NPS page is developed
    public NPSReportPage() {
        totalResponses = By.id("");
        npsScore = By.id("");
        percentagePromoters = By.id("");
        percentagePassives = By.id("");
        percentageDetractors = By.id("");
    }

    public boolean isTotalResponsesCorrect(String expectedValue) {
        return findElement(totalResponses).getText().equals(expectedValue);
    }

    public boolean isNPSScoreCorrect(String expectedValue) {
        return findElement(npsScore).getText().equals(expectedValue);
    }

    public boolean isPercentagePromotersCorrect(String expectedValue) {
        return findElement(percentagePromoters).getText().equals(expectedValue);
    }

    public boolean isPercentagePassivesCorrect(String expectedValue) {
        return findElement(percentagePassives).getText().equals(expectedValue);
    }

    public boolean isPercentageDetractorsCorrect(String expectedValue) {
        return findElement(percentageDetractors).getText().equals(expectedValue);
    }
}
