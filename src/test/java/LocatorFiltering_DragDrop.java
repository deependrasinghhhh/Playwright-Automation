import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

public class LocatorFiltering_DragDrop {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("file:///c%3A/Users/CCST/Desktop/Playwright/PlaywrightMaterial/LocatorFiltering.html");

            Locator sourceContainer = page.locator("#sourceContainer");
            Locator targetContainer = page.locator("#targetContainer");


            page.locator("#item1").dragTo(targetContainer);
            page.locator("#item2").dragTo(sourceContainer);
            page.locator("#item3").dragTo(targetContainer);

            if (targetContainer.isVisible()){
                System.out.println("Successfully moved");
            } else {
                System.out.println("Failed");
            }


            page.waitForTimeout(2000);

            browser.close();
        } catch (Exception e) {
            System.err.println("Test Failed with an Exception:");
            e.printStackTrace();
        }
    }
}