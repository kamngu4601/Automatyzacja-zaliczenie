package selenium.test.automation.pages.environment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.test.automation.pages.DefaultPage;

public class AddEnvironmentPage extends DefaultPage {

    @FindBy(id = "name")
    public WebElement nameFiled;

    @FindBy(id = "description")
    public WebElement descriptionField;

    @FindBy(id = "save")
    public WebElement saveButton;

    public AddEnvironmentPage(WebDriver driver) {
        super(driver);
    }

    public void fillAddEnvironmentForm(String name, String description) {
        fillElement(nameFiled, name);
        fillElement(descriptionField, description);
    }

    public void submitAddEnvironmentForm() {
        clickElement(saveButton);
    }
}