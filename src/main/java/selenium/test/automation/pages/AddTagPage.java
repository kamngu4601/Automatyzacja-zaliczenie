package selenium.test.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddTagPage extends DefaultPage {

    @FindBy(id = "name")
    public WebElement nameField;

    @FindBy(id = "save")
    public WebElement saveButton;

    public AddTagPage(WebDriver driver) {
        super(driver);
    }

    public void fillAddTagForm(String name) {
        fillElement(nameField, name);
    }

    public void submitAddTagForm() {
        clickElement(saveButton);
    }

}
