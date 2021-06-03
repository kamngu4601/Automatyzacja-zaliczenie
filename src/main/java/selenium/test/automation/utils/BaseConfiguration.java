package selenium.test.automation.utils;

public class BaseConfiguration {

    public static String DRIVERS_DIRECTORY = "drivers/";
    public static String DATA_DIRECTORY = "data/";
    public static String CONFIGURATION_DIRECTORY = "configuration/";

    public static String SCREENSHOT_DIRECTORY = "/screenshot/";
    public static String SCREENSHOT_PATH = System.getProperty("user.dir") + SCREENSHOT_DIRECTORY;

    public static String REPORT_DIRECTORY = "/report/";
    public static String REPORT_PATH = System.getProperty("user.dir") + REPORT_DIRECTORY;

    public static String RECORDING_DIRECTORY = "/record";
    public static String RECORDING_PATH = System.getProperty("user.dir") + REPORT_DIRECTORY;
}
