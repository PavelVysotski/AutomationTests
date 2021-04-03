package pageEntity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SignUpPage {

    private WebDriver webDriver;
    private WebDriverWait wait;

    public SignUpPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//input[@type='email']")
    private WebElement enterEmail;

    @FindBy(xpath = "//span[contains(text(),'Продолжить через электронную почту')]")
    private WebElement continueEmail;

    private By waitEnterPass = By.xpath("//h1[contains(text(),'Введите пароль')]");

    @FindBy(id = "password")
    private WebElement enterPassword;

    @FindBy(xpath = "//span[contains(text(),'Войти')]")
    private WebElement enter;

    private By waitStartPage = By.xpath("//*[@id='b2indexPage']/div[2]/div/div/div[1]");  //TODO

    @FindBy(xpath = "//div[@class='bui-dropdown bui-dropdown--end']//div[@class='bui-avatar-block__text']")
    private WebElement loginName;

    private By waitLoginName = By.xpath("//div[@class='bui-dropdown bui-dropdown--end']//div[@class='bui-avatar-block__text']");

    public void fillEmail (String email){
        enterEmail.sendKeys(email);
        continueEmail.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitEnterPass));
    }

    public void fillPassword(String password){
        enterPassword.sendKeys(password);
        enter.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitStartPage));
    }

    public String confirmLogin(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitLoginName));
        String text = loginName.getText();
        return text;
    }

}
