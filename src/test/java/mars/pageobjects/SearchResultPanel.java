package mars.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class SearchResultPanel extends PageObject {
    private final By SEARCH_RESULT_PANEL = By.id("content");
    public String getMessage() {
        return $(SEARCH_RESULT_PANEL).getText();
    }
}
