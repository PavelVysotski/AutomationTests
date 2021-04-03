package pageEntity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FindRoomPage {

    private WebDriver webDriver;

    public FindRoomPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchPlace;

    @FindBy(xpath = "//*[@id='frm']/div[1]/div[2]/div[1]") //TODO
    private WebElement startDate;

    @FindBy(xpath = "//div[@class='bui-calendar']//div[@data-bui-ref='calendar-next']")
    private WebElement searchMonthNext;

    @FindBy(xpath = "//div[@class='bui-calendar']//td[@data-date='2021-09-24']")
    private WebElement chooseStartDate;

    @FindBy(xpath = "//div[@class='bui-calendar']//td[@data-date='2021-10-01']")
    private WebElement chooseEndDate;

    @FindBy(xpath = "//label[@id='xp__guests__toggle']")
    private WebElement guestButton;

    @FindBy(xpath = "//button[@aria-label='Взрослых: уменьшить количество']")
    private WebElement adultNumberMinus;

    @FindBy(xpath = "//button[@aria-label='Детей: увеличить количество']")
    private WebElement childrenNumberPlus;

    @FindBy(xpath = "//button[@class='sb-searchbox__button ']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@data-block-id='heading']//h1")
    private WebElement successSearch;

    private By dateDescription = By.xpath("//div[@class='bui-calendar__month']");

    public void fillLocation(String location) {
        searchPlace.sendKeys(location);
    }

    public void chooseStartDate(String startNameMonth) {
        startDate.click();
        searchMonth(startNameMonth);
        chooseStartDate.click();
    }

    public void chooseEndDate(String endNameMonth){
        searchMonth(endNameMonth);
        chooseEndDate.click();
    }

    public void chooseGuests() {
        guestButton.click();
        adultNumberMinus.click();
        childrenNumberPlus.click();
    }

    public void startSearch() {
        searchButton.click();
    }

    public String confirmLogin() {
        String text = successSearch.getText();
        return text;
    }

    private void searchMonth(String monthName){
        List<WebElement> startMonth = webDriver.findElements(dateDescription);
        for (WebElement date : startMonth) {
            if (!date.getText().contains(monthName)) {
                searchMonthNext.click();
                searchMonth(monthName);
            }
            break;
        }
    }

}
