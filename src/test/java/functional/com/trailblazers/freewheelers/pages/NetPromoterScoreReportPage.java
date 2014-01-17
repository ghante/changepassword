package functional.com.trailblazers.freewheelers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by rameshb on 1/16/14.
 */
public class NetPromoterScoreReportPage extends BasePage {
    private By totalResponses;
    private By npsScore;
    private By percentagePromoters;
    private By percentagePassives;
    private By percentageDetractors;

    // TODO - Complete the element ids once NPS page is developed
    public NetPromoterScoreReportPage(WebDriver driver) {
        super(driver);
        totalResponses = By.id("");
        npsScore = By.id("");
        percentagePromoters = By.id("");
        percentagePassives = By.id("");
        percentageDetractors = By.id("");
    }

    public boolean totalResponsesCorrect(String expectedValue) {
        return findElement(totalResponses).getText().equals(expectedValue);
    }

    public boolean npsScoreCorrect(String expectedValue) {
        return findElement(npsScore).getText().equals(expectedValue);
    }

    public boolean percentagePromotersCorrect(String expectedValue) {
        return findElement(percentagePromoters).getText().equals(expectedValue);
    }

    public boolean percentagePassivesCorrect(String expectedValue) {
        return findElement(percentagePassives).getText().equals(expectedValue);
    }

    public boolean percentageDetractorsCorrect(String expectedValue) {
        return findElement(percentageDetractors).getText().equals(expectedValue);
    }
}
