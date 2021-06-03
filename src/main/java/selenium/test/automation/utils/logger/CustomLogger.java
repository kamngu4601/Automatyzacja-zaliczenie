package selenium.test.automation.utils.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import selenium.test.automation.utils.BaseConfiguration;
import selenium.test.automation.utils.files.FileHelper;

public class CustomLogger {

    private static Logger logger;

    public CustomLogger(String name) {
        System.setProperty("log4j.configurationFile", new FileHelper().getResourceFilePath(BaseConfiguration.CONFIGURATION_DIRECTORY + "log4j2.properties"));
        logger = LogManager.getLogger(name);
    }

    public void info (String message){
        logger.info(message);
    }

    public void error (String message) {
        logger.error(message);
    }
}
