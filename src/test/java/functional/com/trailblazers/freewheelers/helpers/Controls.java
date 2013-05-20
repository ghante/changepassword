package functional.com.trailblazers.freewheelers.helpers;

import org.openqa.selenium.WebElement;

public class Controls {

    public static void fillField(WebElement field, String value) {
        field.clear();
        field.sendKeys(value);
    }


    public static void check(WebElement checkBox) {
        if(!checkBox.isSelected()) {
            checkBox.click();
        }
    }
}
