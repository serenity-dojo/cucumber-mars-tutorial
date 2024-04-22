package mars.stepdefinitions;

import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mars.actions.Navigation;
import mars.model.SearchDates;
import mars.pageobjects.SearchForm;
import mars.pageobjects.SearchResultPanel;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.SoftAssertions;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchStepDefinitions {

    @Steps
    Navigation navigation;

    SearchForm searchForm;
    SearchResultPanel searchResultPanel;

    @Given("Martin is on the MarsAir homepage")
    public void martinIsOnTheMarsAirHomepage() {
        navigation.openMarsApplication();
    }

    @When("he wants to look for available flights")
    public void heWantsToLookForAvailableFlights() {
        // No action required
    }

    @Then("he should be able to specify the departure and return months that he wants to travel")
    public void heShouldBeAbleToSpecifyTheDepartureAndReturnMonthsThatHeWantsToTravel() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(searchForm.getDepartureMonthDropdown().isVisible())
                .withFailMessage("Departure dropdown should be visible")
                .isTrue();
        softly.assertThat(searchForm.getReturnMonthDropdown().isVisible())
              .withFailMessage("Return dropdown should be visible").isTrue();
        softly.assertAll();
    }

    @ParameterType("departure|return")
    public String departureOrReturn(String departureOrReturn) {
        return departureOrReturn;
    }

    @Then("he should see the following {departureOrReturn} months:")
    public void heShouldSeeTheFollowingMonths(String departureOrReturn,
                                              List<String> expectedMonths) {

        List<String> actualMonths = (departureOrReturn.equals("departure")) ?
                searchForm.getDepartureMonths() : searchForm.getReturnMonths();

        assertThat(actualMonths).containsExactlyElementsOf(expectedMonths);
    }

    @DataTableType
    public SearchDates searchDatesEntry(Map<String, String> entry) {
        return new SearchDates(
                entry.get("Departure"),
                entry.get("Return")
        );
    }

    @When("he looks for available flights for the following dates:")
    public void heLooksForAvailableFlightsForTheFollowingDates(List<SearchDates> searchDates) {
        SearchDates searchDate = searchDates.get(0);
        searchForm.selectDepartureMonth(searchDate.departureMonth());
        searchForm.selectReturnMonth(searchDate.returnMonth());
        searchForm.performSearch();
    }

    @Then("he should be told {string}")
    public void heShouldBeTold(String expectedMessage) {
        assertThat(searchResultPanel.getMessage()).contains(expectedMessage);
    }

    @Then("he should be told the discount amount  is {int}%")
    public void heShouldBeToldTheDiscountAmountIs(int percentAmount) {
        assertThat(searchResultPanel.getMessage()).contains(percentAmount + "% discount");
    }

    @Then("he should be told the code is invalid")
    public void heShouldBeToldTheCodeIsInvalid() {
        assertThat(searchResultPanel.getMessage()).contains("is not valid");
    }

    @When("he looks for an available flight using promo code {string}")
    public void heLooksForAnAvailableFlightUsingPromoCode(String code) {
        searchForm.selectDepartureMonth("July");
        searchForm.selectReturnMonth("December (two years from now)");
        searchForm.enterPromotionCode(code);
        searchForm.performSearch();
    }
}
