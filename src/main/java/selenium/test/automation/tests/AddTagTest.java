package selenium.test.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.test.automation.pages.AddTagPage;
import selenium.test.automation.pages.DashboardPage;
import selenium.test.automation.pages.TagListPage;

import java.util.Date;

public class AddTagTest extends DefaultTest {

    @Test
    public void successAddTagTest() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.goToTagListPage();

        TagListPage tagListPage = new TagListPage(driver);
        tagListPage.goToAddTagPage();

        AddTagPage addTagPage = new AddTagPage(driver);
        addTagPage.fillAddTagForm(new Date().getTime() + "_tag");
        addTagPage.submitAddTagForm();

        tagListPage = new TagListPage(driver);
        Assert.assertTrue(tagListPage.isInfoBoxDisplayed());

    }

}
