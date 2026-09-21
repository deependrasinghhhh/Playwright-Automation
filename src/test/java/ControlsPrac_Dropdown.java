import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

public class ControlsPrac_Dropdown {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("file:///c%3A/Users/CCST/Desktop/Playwright/PlaywrightMaterial/ControlsPractice.html");

            Locator dropdown = page.locator("#module");


            dropdown.selectOption("CCST");


            dropdown.selectOption(new SelectOption().setLabel("DAI"));


            dropdown.selectOption(new SelectOption().setIndex(3));


            page.waitForTimeout(2000);

            browser.close();
        } catch (Exception e) {
            System.err.println("Test Failed with an Exception:");
            e.printStackTrace();
        }
    }
}