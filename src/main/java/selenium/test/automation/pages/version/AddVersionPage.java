package selenium.test.automation.pages.version;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.test.automation.pages.DefaultPage;

public class AddVersionPage extends DefaultPage {

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "description")
    private WebElement descriptionField;

    @FindBy(id = "save")
    private WebElement saveButton;

    public AddVersionPage(WebDriver driver) {
        super(driver);
    }

    public void fillAddVersionForm (String name, String description) {
        fillElement(nameField, name);
        fillElement(descriptionField, description);
    }

    public void submitAddVersionForm () {
        clickElement(saveButton);
    }

}
