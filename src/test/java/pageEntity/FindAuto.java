package pageEntity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FindAuto {

    private WebDriver webDriver;
    private WebDriverWait wait;

    public FindAuto(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//ul[@class='bui-tab__nav']//li[3]//a[@class='bui-tab__link']")
    private WebElement carRental;

    @FindBy(xpath = "//input[@placeholder='Место получения']")
    private WebElement placeOfReceipt;

    @FindBy(xpath = "//*[@id='frm']/div[2]/div[3]/div/div[2]/div/div/div/div[1]/div/button") //TODO
    private WebElement openCalendar;

    @FindBy(xpath = "//div[@class='c2-button c2-button-further']")
    private WebElement nextButton;

    @FindBy(xpath = "//*[@id='frm']/div[2]/div[3]/div/div[2]/div/div/div/div[2]/div[2]/div[3]/div/div/div[3]/table/tbody/tr[1]/td[5]")
    private WebElement startDate;

    @FindBy(xpath = "//div[@class='sb-searchbox-submit-col -submit-button ']")
    private WebElement submitButton;

    private By autoList = By.xpath("//ul[@class='c-autocomplete__list sb-autocomplete__list']/li");
    private By waitAutoList = By.xpath("//ul[@class='c-autocomplete__list sb-autocomplete__list']");


    private By waitCarRentalPage = By.xpath("//div[@class='xpi__searchbox rentalcars']");

    public void chooseRentalCar() {
        carRental.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitCarRentalPage));
    }

    public void choosePlaceOfReceipt(String place) throws InterruptedException {
        placeOfReceipt.click();
        placeOfReceipt.sendKeys(place);
        wait.until(w -> w.findElements(autoList).size() > 1);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(waitAutoList));
        List<WebElement> list = webDriver.findElements(autoList);
        for (WebElement pl : list) {
            if (pl.getAttribute("data-value").contains(place)) {
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

                break;
            }
        }
    }

    public void chooseDate() {
        openCalendar.click();
        nextButton.click();
        submitButton.click();
    }

}
