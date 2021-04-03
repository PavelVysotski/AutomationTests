package pageEntity;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FindAirTickets {

    private WebDriver webDriver;
    private WebDriverWait wait;

    public FindAirTickets(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//*[@id='b2indexPage']/header/nav[2]/ul/li[2]/a")
    private WebElement airTicketsButton;

    @FindBy(xpath = "//div[@aria-label='Откуда']")
    private WebElement originAirportButton;

    @FindBy(xpath = "//div[@aria-label='Место назначения']")
    private WebElement destinationAirportButton;

    @FindBy(xpath = "//input[@aria-label='Откуда']")
    private WebElement enterOriginAirport;

    @FindBy(xpath = "//input[@aria-label='Место назначения']")
    private WebElement enterDestinationAirport;

    private By waitAirTicketsPage = By.xpath("//div[@class='keel-container s-t-bp']");
    private By airportList = By.xpath("//ul[@class='flight-smarty']/li");

    public void chooseAirTickets() {
        airTicketsButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(waitAirTicketsPage));
    }

    public void chooseDirection(String origin, String destination) throws InterruptedException {
        originAirportButton.click();
        enterOriginAirport.clear();
        enterOriginAirport.sendKeys(origin);
        chooseAirport(origin);

        destinationAirportButton.click();
        enterDestinationAirport.clear();
        enterDestinationAirport.sendKeys(destination);
        chooseAirport(destination);
        int i =1;
    }

    public void chooseAirport(String airportName) {
        List<WebElement> airports = new ArrayList<>();
        wait.until(w -> w.findElements(airportList).size() > 1);
        airports = webDriver.findElements(airportList);
        for (WebElement ai : airports) {
            if (ai.getAttribute("aria-label").toLowerCase().contains(airportName.toLowerCase())) {
                ai.click();
//                JavascriptExecutor executor = (JavascriptExecutor)webDriver;
//                executor.executeScript("arguments[0].click();", ai);
                break;
            }
        }
    }


}
