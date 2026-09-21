import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WaitMechanisms {

    public static void main(String[] args) {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate("file:///c%3A/Users/CCST/Desktop/Playwright/PlaywrightMaterial/welcome.html");

            Locator message = obj_page.locator("#message");

            long start = System.currentTimeMillis();

            message.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
            );

            String labelText = message.innerText();

            long elapsed = System.currentTimeMillis() - start;

            System.out.println("Label text: " + labelText);
            System.out.println("Time waited: " + elapsed + "MS");

            obj_context.close();
            obj_browser.close();

        } catch (Exception e) {
            System.out.println("Test Failed with an Exception");
            e.printStackTrace();
        }
    }
}