import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class nested_Iframe {
    public static void main(String[] args) throws InterruptedException {
        try (Playwright playwright = Playwright.create()) {
            Browser obj_browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_Context = obj_browser.newContext();
            Page obj_page = obj_Context.newPage();

            obj_page.navigate("file:///c%3A/Users/CCST/Downloads/New%20folder%20%282%29/SeleniumMaterial/iFrameDemo.html");

            FrameLocator outerFrame = obj_page.frameLocator("iframe[id='outerFrame']");
            System.out.println("Located outer frame");

            Locator outerHeading = outerFrame.locator("h4");
            outerHeading.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            FrameLocator innerFrame = outerFrame.frameLocator("iframe#innerFrame");
            System.out.println("LOCATED INNER (NESTED) FRAME.");

            Locator innerFrameBtn = innerFrame.locator("#innerFrameBtn");
            innerFrameBtn.click();
            String innerResult = innerFrame.locator("#innerFrameResult").textContent();

            assertThat(innerFrameBtn).isVisible();

            Thread.sleep(2000);

            boolean backInOuter = outerFrame.locator("iframe#innerFrame").count() > 0;
            assertTrue(backInOuter);

            Thread.sleep(2000);

            Locator mainBtn = obj_page.locator("#mainBtn");

            assertThat(mainBtn).isVisible();

            obj_browser.close();
        }
    }
}