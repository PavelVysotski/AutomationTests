package bookingTest;

import config.Driver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected RemoteWebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = Driver.getDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        driver.close();
        driver.quit();
        driver = null;
    }
}
