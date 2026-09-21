import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Locator_Filtering {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {
            Browser obj_browser = obj_playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate("file:///c%3A/Users/CCST/Desktop/Playwright/PlaywrightMaterial/LocatorFiltering.html");
            obj_page.waitForLoadState();


            Locator sourceContainer = obj_page.locator(".container")
                    .filter(new Locator.FilterOptions()
                            .setHas(obj_page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("TO DO"))));


            Locator targetContainer = obj_page.locator(".container")
                    .filter(new Locator.FilterOptions()
                            .setHas(obj_page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Done"))));

            System.out.println("Source container visible: " + sourceContainer.isVisible());
            System.out.println("Target container visible: " + targetContainer.isVisible());

            Locator itemToDrag = obj_page.locator("#item3");
            String itemText = itemToDrag.innerText().trim();
            System.out.println("Item to drag: " + itemText);


            itemToDrag.dragTo(targetContainer);

            Locator resultLocator = obj_page.locator("#result");
            assertThat(resultLocator).hasText("Write Selenium Tests moved to Done");

            System.out.println("Result text: " + resultLocator.innerText());
            System.out.println("Test Passed Successfully!");

            obj_page.waitForTimeout(2000);
            obj_browser.close();
        } catch (Exception e) {
            System.out.println("Test Failed with an Exception");
            e.printStackTrace();
        }
    }
}