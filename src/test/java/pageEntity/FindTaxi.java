package pageEntity;

import config.ConfigFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindTaxi {

    private WebDriver webDriver;
    private WebDriverWait wait;
    ConfigFileReader configFileReader = new ConfigFileReader();

    public FindTaxi(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//ul[@class='bui-tab__nav']//li[5]//a[@class='bui-tab__link']")
    private WebElement taxiButton;

    @FindBy(xpath = "//label[@for='sortbutton_2']")
    private WebElement taxiOptions;

    @FindBy(xpath = "//div[@data-num-passengers='6']//button")
    private WebElement submitButton;

    private By waitSubmitButton = By.xpath("//div[@data-num-passengers='6']//button");

    public void chooseTaxiOptions() throws InterruptedException {
        taxiButton.click();
        taxiOptions.click();
        submitButton.click();
    }
}
