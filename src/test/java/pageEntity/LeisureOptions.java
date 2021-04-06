package pageEntity;

import config.ConfigFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeisureOptions {

    private WebDriver webDriver;
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

    @FindBy(xpath = "//span[contains(text(),'Северная Америка')]/parent::button")
    private WebElement buttonNorthAmerica;

    @FindBy(xpath = "//h1[@class='styled-bui-1a85whl']")
    private WebElement successSearchPropose;

    @FindBy(xpath = "//ol[@class='bui-group bui-breadcrumb__list bui-group--small bui-group--inline']")
    private WebElement successSearchGlobal;

    private By proposedTownNorthAmerica = By.xpath("//div[@class='bui-tab__content bui-tab__content--selected']/div/div/div");
    private By waitTownList = By.xpath("//ul[@class='styled-bui-17fljn7']");

    public void enterSearch(String townName) {
        leisureOptionsButton.click();
        searchField.clear();
        searchField.click();
        searchField.sendKeys(townName);
        webDriver.findElements(waitTownList);
        findButton.click();
    }

    public void choiceFromTheProposed(String proposeTown) {
        leisureOptionsButton.click();
        buttonNorthAmerica.click();
        WebElement proposeButtonTown = webDriver.findElement(By.xpath("//a[@title='" + proposeTown + "']"));
        proposeButtonTown.click();
    }

    public String confirmTestGlobal() {
        String text = successSearchGlobal.getText();
        return text;
    }

    public String confirmTestPropose(){
        String text = successSearchPropose.getText().toLowerCase();
        return text;
    }
}
