package pageEntity;

import config.ConfigFileReader;
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
    ConfigFileReader configFileReader = new ConfigFileReader();

    public FindAirTickets(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(configFileReader.getImplicitlyWait()));
    }

    @FindBy(xpath = "//ul[@class='bui-tab__nav']//li[2]//a[@class='bui-tab__link']")
    private WebElement airTicketsButton;

    @FindBy(xpath = "//div[@aria-label='Откуда']")
    private WebElement originAirportButton;

    @FindBy(xpath = "//div[@aria-label='Место назначения']")
    private WebElement destinationAirportButton;

    @FindBy(xpath = "//input[@aria-label='Откуда']")
    private WebElement enterOriginAirport;

    @FindBy(xpath = "//input[@aria-label='Место назначения']")
    private WebElement enterDestinationAirport;

    @FindBy(xpath = "//div[@class='_iaf _iEc _ia1 _i7r _iv1 _j0z _iPK _j0x _j0B _iai']")
    private WebElement dateButton;

    @FindBy(xpath = "//div[@class='_ia1 _iBD _iai']//div[@aria-label='Дата вылета']")
    private WebElement startDate;

    @FindBy(xpath = "//div[@class='_ia1 _iBD _iai']//div[@aria-label='Дата обратно вылета']")
    private WebElement endDate;

    @FindBy(xpath = "//div[@class='col _iac _iad _iae _iaa _iab _iys _iyv _iyq _iAU _iAV _izh _iaR _h-8 _63 centre']")
    private WebElement submitButton;

    private By waitAirTicketsPage = By.xpath("//div[@class='keel-container s-t-bp']");
    private By airportList = By.xpath("//ul[@class='flight-smarty']/li");


    public void chooseAirTickets() {
        airTicketsButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(waitAirTicketsPage));
    }

    public void chooseDirection(String origin, String destination) {
        originAirportButton.click();
        enterOriginAirport.clear();
        enterOriginAirport.sendKeys(origin);
        chooseAirport(origin);

        destinationAirportButton.click();
        enterDestinationAirport.clear();
        enterDestinationAirport.sendKeys(destination);
        chooseAirport(destination);
        webDriver.findElement(By.xpath("//body")).click();

    }

    public void chooseAirport(String airportName) {
        wait.until(w -> w.findElements(airportList).size() > 1);
        List<WebElement> airports = webDriver.findElements(airportList);
        for (WebElement air : airports) {
            if (air.getAttribute("aria-label").toLowerCase().contains(airportName.toLowerCase())) {
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                air.click();
//                JavascriptExecutor executor = (JavascriptExecutor)webDriver;
//                executor.executeScript("arguments[0].click();", ai);
                break;
            }
        }
    }

    public void chooseDate(String firstDate, String secondDate) {
        dateButton.click();
        startDate.click();
        startDate.clear();
        startDate.sendKeys(firstDate);
        endDate.click();
        endDate.clear();
        endDate.sendKeys(secondDate);
        submitButton.click();

    }


}
