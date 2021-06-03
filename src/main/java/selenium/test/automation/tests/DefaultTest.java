package selenium.test.automation.tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import selenium.test.automation.utils.BaseConfiguration;
import selenium.test.automation.utils.drivers.DriverFactory;
import selenium.test.automation.utils.generators.BaseGenerator;
import selenium.test.automation.utils.logger.CustomLogger;
import selenium.test.automation.utils.screen.CustomScreenshot;
import selenium.test.automation.utils.types.BrowserType;
import selenium.test.automation.utils.video.CustomRecorder;


import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class DefaultTest {

    protected static WebDriver driver;
    protected static CustomScreenshot screenshot;
    protected static BaseGenerator baseGenerator;
    protected static CustomLogger logger;
    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;


    protected boolean testCondition;

    private static String timestamp;


    @BeforeTest
    @Parameters({"browser"})
    public void setUpTest(String browserType) {
        timestamp = String.valueOf(new Date().getTime());

        extentReports = new ExtentReports(BaseConfiguration.REPORT_PATH + timestamp + "/" + "report.html", true);
        extentReports.addSystemInfo("Executor:", "tester");
        extentReports.addSystemInfo("Date:", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        extentReports.addSystemInfo("Browser:", browserType);

        screenshot = new CustomScreenshot();
        baseGenerator = new BaseGenerator();

        logger = new CustomLogger("TEST");
        driver = new DriverFactory().getDriver(BrowserType.valueOf(browserType));
        driver.get("http://beta.demo.testarena.pl/login");


        //poczatek nagrania
        try {
            CustomRecorder.startRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUpMethod(Method method) {
        logger.info("Test '" + method.getName() + "' has started");
        extentTest = extentReports.startTest(method.getName());
    }

    @AfterMethod
    @Parameters({"makeScreenshot"})
    public void tearDownMethod(ITestResult result, String makeScreenshot) {
        if (result.getStatus() == ITestResult.FAILURE) {
            if (makeScreenshot.equalsIgnoreCase("ON_FAIL") || makeScreenshot.equalsIgnoreCase("ALWAYS")) {
                screenshot.make(driver, timestamp, result.getMethod().getMethodName() + "_FAILURE");
            }
            logger.error("Test '" + result.getMethod().getMethodName() + "' has finished with status: FAILURE");
            extentTest.log(LogStatus.FAIL, result.getMethod().getMethodName() +
                    (makeScreenshot.equalsIgnoreCase("ON_FAIL") || makeScreenshot.equalsIgnoreCase("ALWAYS") ?
                            extentTest.addScreenCapture("." + BaseConfiguration.SCREENSHOT_DIRECTORY + result.getMethod().getMethodName() + "_FAILURE" + ".png") :
                            ""
                    )
            );
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.info("Test '" + result.getMethod().getMethodName() + "' has finished with status: SKIP");
            extentTest.log(LogStatus.SKIP, result.getMethod().getMethodName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            if (makeScreenshot.equalsIgnoreCase("ALWAYS")) {
                screenshot.make(driver, timestamp, result.getMethod().getMethodName());
            }
            logger.info("Test '" + result.getMethod().getMethodName() + "' has finished with status: SUCCESS");
            extentTest.log(LogStatus.PASS, result.getMethod().getMethodName() +
                    (makeScreenshot.equalsIgnoreCase("ALWAYS") ?
                            extentTest.addScreenCapture("." + BaseConfiguration.SCREENSHOT_DIRECTORY + result.getMethod().getMethodName() + ".png") :
                            ""
                    )
            );
        }
        extentReports.endTest(extentTest);

        //koniec nagrania
        if (result.getStatus() == ITestResult.SUCCESS)
            testCondition = true;
        else if (result.getStatus() == ITestResult.FAILURE)
            testCondition = false;

        try {
            CustomRecorder.deleteIfSuccess(testCondition);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterTest
    public void tearDownTest() {
        driver.quit();
        extentReports.flush();
        extentReports.close();
    }
}