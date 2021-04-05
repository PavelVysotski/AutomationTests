package pageEntity;

import config.ConfigFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LeisureOptions {

    private WebDriver webDriver;
    private WebDriverWait wait;
    ConfigFileReader configFileReader = new ConfigFileReader();

    public LeisureOptions(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//ul[@class='bui-tab__nav']//li[4]//a[@class='bui-tab__link']")
    private WebElement leisureOptionsButton;

    @FindBy(xpath = "//input[@name='query']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement findButton;

    @FindBy(xpath = "//ul[@role='tablist']//li[2]")
    private WebElement africaButton;

    @FindBy(xpath = "//a[@title='Лас-Вегас']")
    private WebElement buttonLasVegas;

    private By proposedTownNorthAmerica = By.xpath("//div[@class='bui-tab__content bui-tab__content--selected']/div/div/div");

    public void enterSearch(String townName) throws InterruptedException {
        leisureOptionsButton.click();
        searchField.clear();
        searchField.click();
        searchField.sendKeys(townName);
        Thread.sleep(5000);
        findButton.click();

    }

    public void choiceFromTheProposed() {
        leisureOptionsButton.click();
        africaButton.click();
        buttonLasVegas.click();
    }


}
