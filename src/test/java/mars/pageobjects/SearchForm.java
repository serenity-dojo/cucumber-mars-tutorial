package mars.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementState;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class SearchForm extends PageObject {

    private final By DEPARTURE_MONTH_DROPDOWN = By.id("departing");
    private final By RETURN_MONTH_DROPDOWN = By.id("returning");
    private final By PROMO_CODE_INPUT = By.cssSelector("input[name='promotional_code']");
    private final By SEARCH_BUTTON = By.cssSelector("input[value='Search']");

    public WebElementState getDepartureMonthDropdown() {
        return $(DEPARTURE_MONTH_DROPDOWN);
    }

    public WebElementState getReturnMonthDropdown() {
        return $(RETURN_MONTH_DROPDOWN);
    }

    public List<String> getDepartureMonths() {
        return getDropdownOptionsFor(DEPARTURE_MONTH_DROPDOWN);
    }

    public List<String> getReturnMonths() {
        return getDropdownOptionsFor(RETURN_MONTH_DROPDOWN);
    }

    @NotNull
    private List<String> getDropdownOptionsFor(By RETURN_MONTH_DROPDOWN) {
        return new Select($(RETURN_MONTH_DROPDOWN))
                .getOptions()
                .stream()
                .map(WebElement::getText)
                .filter(option -> !option.equals("Select..."))
                .collect(Collectors.toList());
    }

    public void selectDepartureMonth(String month) {
        $(DEPARTURE_MONTH_DROPDOWN).selectByVisibleText(month);
    }

    public void selectReturnMonth(String month) {
        $(RETURN_MONTH_DROPDOWN).selectByVisibleText(month);
    }

    public void performSearch() {
        $(SEARCH_BUTTON).click();
    }

    public void enterPromotionCode(String code) {
        $(PROMO_CODE_INPUT).type(code);
    }
}
