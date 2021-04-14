package pageEntity;

import config.ConfigFileReader;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
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
    private WebElement originAirport;

    @FindBy(xpath = "//input[@aria-label='Место назначения']")
    private WebElement destinationAirport;

    @FindBy(xpath = "//div[@class='_ia1 _iBD _iai']//div[@aria-label='Дата вылета']")
    private WebElement startDate;

    @FindBy(xpath = "//div[@class='_ia1 _iBD _iai']//div[@aria-label='Дата обратно вылета']")
    private WebElement endDate;

    @FindBy(xpath = "//div[@class='col _iac _iad _iae _iaa _iab _iys _iyv _iyq _iAU _iAV _izh _iaR _h-8 _63 centre']")
    private WebElement submitButton;

    private By dateButton = By.xpath("//div[@class='_iaf _iEc _ia1 _i7r _iv1 _j0z _iPK _j0x _j0B _iai']");
    private By waitAirTicketsPage = By.xpath("//div[@class='keel-container s-t-bp']");
    private By airportList = By.xpath("//ul[@class='flight-smarty']/li");
    private By confirmSearch = By.xpath("//h3[@class='title' and contains(text(),'Авиакомпании')]");

    public void chooseAirTickets() {
        airTicketsButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(waitAirTicketsPage));
    }

    public void chooseDirection(String origin, String destination)  {
        originAirportButton.click();
        originAirport.clear();
        originAirport.sendKeys(origin);
        chooseAirport(origin);

        destinationAirportButton.click();
        destinationAirport.clear();
        destinationAirport.sendKeys(destination);
        chooseAirport(destination);
        webDriver.findElement(By.xpath("//body")).click();
    }

    public void chooseDate(String firstDate, String secondDate) {
        webDriver.findElement(dateButton).click();
        startDate.click();
        startDate.clear();
        startDate.sendKeys(firstDate);
        endDate.click();
        endDate.clear();
        endDate.sendKeys(secondDate);
        submitButton.click();
    }

    public Boolean confirmTest() {
        Boolean isPresent = webDriver.findElement(confirmSearch).isEnabled();
        return isPresent;
    }

    private void chooseAirport(String airportName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(airportList));
        wait.until(w -> w.findElements(airportList).size() > 1);
        List<WebElement> airports = webDriver.findElements(airportList);
        for (WebElement air : airports) {
            if (air.getAttribute("aria-label").toLowerCase().contains(airportName.toLowerCase())) {
                wait.until(ExpectedConditions.presenceOfElementLocated(airportList));
                air.click();
            }
            break;
        }
    }
}
