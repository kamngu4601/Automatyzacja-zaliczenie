package selenium.test.automation.pages.environment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.test.automation.pages.DefaultPage;

public class EnvironmentListPage extends DefaultPage {

    @FindBy(xpath = "//html/body/div[1]/section/article/div[1]/nav/ul/li/a")
    private WebElement addEnvironmentPageLink;

    @FindBy(id="j_info_box")
    private WebElement infoBox;

    public EnvironmentListPage(WebDriver driver) {
        super(driver);
    }

    public void goToAddEnvironmentPage() {
        clickElement(addEnvironmentPageLink);
    }

    public boolean isInfoBoxDisplayed(){
        return isElementDisplayed(infoBox);
    }
}