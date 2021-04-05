package pageEntity;

import config.ConfigFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FindTaxi {

    private WebDriver webDriver;
    ConfigFileReader configFileReader = new ConfigFileReader();

    public FindTaxi(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//ul[@class='bui-tab__nav']//li[5]//a[@class='bui-tab__link']")
    private WebElement taxiButton;

    @FindBy(xpath = "//ul[@class='bui-segmented-control__list']//li[3]//label[@for='sortbutton_2']")
    private WebElement chooseTaxiOptions;

    @FindBy(xpath = "//div[@data-num-passengers='6']/div//div[@class='bui-group bui-group--inline bui-card__actions www-taxi-types__book-cta']/button")
    private WebElement findTaxiButton;

    private By chooseTaxiList = By.xpath("//ul[@class='bui-segmented-control__list']/li");


    public void chooseTaxiOptions() throws InterruptedException {
        taxiButton.click();
        chooseTaxiOptions.click();
        Thread.sleep(5000);
        findTaxiButton.click();
    }

}
