package pageEntity;

import config.ConfigFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FindAuto {

    private WebDriver webDriver;
    private WebDriverWait wait;
    ConfigFileReader configFileReader = new ConfigFileReader();

    public FindAuto(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//ul[@class='bui-tab__nav']//li[3]//a[@class='bui-tab__link']")
    private WebElement carRental;

    @FindBy(xpath = "//input[@placeholder='Место получения']")
    private WebElement placeReceive;

    @FindBy(xpath = "//div[@class='xp__dates-inner']")
    private WebElement calendar;

    @FindBy(xpath = "//div[@class='sb-searchbox-submit-col -submit-button ']")
    private WebElement submitButton;

    @FindBy(xpath = "//select[@name='checkinTime']")
    private WebElement startTimeHour;

    @FindBy(xpath = "//select[@name='checkinTimeMinutes']")
    private WebElement startTimeMinute;

    @FindBy(xpath = "//select[@name='checkoutTime']")
    private WebElement endTimeHour;

    @FindBy(xpath = "//select[@name='checkoutTimeMinutes']")
    private WebElement endTimeMinute;

    @FindBy(xpath = "//input[@class='sb-date-field__input -day' and contains(@title,'День заезда')]")
    private WebElement startDay;

    @FindBy(xpath = "//input[@class='sb-date-field__input -month' and contains(@title,'Месяц заезда')]")
    private WebElement startMonth;

    @FindBy(xpath = "//input[@id='return-location-different']")
    private WebElement radioButtonDifferent;

    private By successFind = By.xpath("//table[@class='ab-SearchSummary']");
    private By autoList = By.xpath("//ul[@class='c-autocomplete__list sb-autocomplete__list -visible']/li");
    private By waitAutoList = By.xpath("//ul[@class='c-autocomplete__list sb-autocomplete__list']");
    private By waitCarRentalPage = By.xpath("//div[@class='xpi__searchbox rentalcars']");

    public void chooseRentalCar() {
        carRental.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitCarRentalPage));
    }

    public void choosePlaceReceive(String place)  {
        placeReceive.click();
        placeReceive.sendKeys(place);
        wait.until(w -> w.findElements(autoList).size() > 0);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(waitAutoList));
        List<WebElement> list = webDriver.findElements(autoList);
        for (WebElement pl : list) {
            if (pl.getAttribute("data-value").toLowerCase().contains((place).toLowerCase())) {
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(configFileReader.getImplicitlyWait()));
                pl.click();
            }
            break;
        }
    }

    public void chooseTime(String startHour, String startMinute, String endHour, String endMinute) {
        calendar.click();
        selectTime(startTimeHour, startHour, startTimeMinute, startMinute);
        selectTime(endTimeHour, endHour, endTimeMinute, endMinute);
        submitButton.click();
    }

    public void setAttribute() throws InterruptedException {
        calendar.click();

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].setAttribute('value','15')", startDay);
        js.executeScript("arguments[0].setAttribute('value','5')", startMonth);
        webDriver.findElement(By.xpath("//body")).click();

    }

    public Boolean confirmTest() {
        Boolean isPresent = webDriver.findElements(successFind).size() > 0;
        return isPresent;
    }

    private void selectTime(WebElement hourElement, String hour, WebElement minuteElement, String minute) {
        Select selectHour = new Select(hourElement);
        selectHour.selectByValue(hour);
        Select selectMinute = new Select(minuteElement);
        selectMinute.selectByValue(minute);
    }
}
