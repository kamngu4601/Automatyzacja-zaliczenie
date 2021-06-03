package selenium.test.automation.utils.screen;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import selenium.test.automation.utils.BaseConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomScreenshot {

    public void make(WebDriver driver, String timestamp, String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(BaseConfiguration.SCREENSHOT_PATH + timestamp + "_" + fileName + ".png"));
            FileUtils.copyFile(screenshot, new File(BaseConfiguration.REPORT_PATH + timestamp + BaseConfiguration.SCREENSHOT_DIRECTORY + fileName + ".png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
