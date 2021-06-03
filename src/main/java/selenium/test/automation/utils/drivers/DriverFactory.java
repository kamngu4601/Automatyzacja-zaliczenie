package selenium.test.automation.utils.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import selenium.test.automation.utils.BaseConfiguration;
import selenium.test.automation.utils.files.FileHelper;
import selenium.test.automation.utils.types.BrowserType;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public WebDriver getDriver(BrowserType browserType) {
        if (browserType == BrowserType.CHROME) {
            System.setProperty("webdriver.chrome.driver", new FileHelper().getResourceFilePath(BaseConfiguration.DRIVERS_DIRECTORY + "chromedriver.exe"));
            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            return driver;
        } else if (browserType == BrowserType.FIREFOX) {
            System.setProperty("webdriver.gecko.driver", new FileHelper().getResourceFilePath(BaseConfiguration.DRIVERS_DIRECTORY + "geckodriver.exe"));
            WebDriver driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            return driver;
        }
        return null;
    }
}