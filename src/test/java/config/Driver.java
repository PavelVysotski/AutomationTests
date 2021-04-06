package config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class Driver {

    private static RemoteWebDriver driver;

    private Driver() {
    }

    public static RemoteWebDriver getDriver() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
        int implicitlyWait = configFileReader.getImplicitlyWait();
        if (driver == null) {
            switch (configFileReader.getDriverType()) {
                case "chrome":
                    driver = new ChromeDriver();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
                    break;
            }
        }
        return driver;
    }
}
