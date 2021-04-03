package pageEntity;

import config.ConfigFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver webDriver;
    private WebDriverWait wait;
    private ConfigFileReader configFileReader = new ConfigFileReader();

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//span[contains(text(),'Войти в аккаунт')]")
    private WebElement enterAccount;

    private By waitHeader = By.xpath("//header[@class='bui-header bui-header--logo-large bui-u-hidden-print']");
    private By waitEnterEmail = By.xpath("//h1[contains(text(),'Войдите или создайте аккаунт')]");

    public void uploadSite(String URL) {
        webDriver.get(configFileReader.getBaseUrl());
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitHeader));
    }

    public void enterAccount(String URL) {
        webDriver.get(configFileReader.getBaseUrl());
        enterAccount.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitEnterEmail));
    }


}
