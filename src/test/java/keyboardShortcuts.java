import com.microsoft.playwright.*;

public class keyboardShortcuts {

    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create()) {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));

            BrowserContext obj_context = obj_browser.newContext();

            Page obj_page = obj_context.newPage();
            obj_page.navigate("file:///c%3A/Users/CCST/Downloads/New%20folder%20%282%29/SeleniumMaterial/keyboardShortcuts.html");


            Locator sourceContainer = obj_page.locator("#sourceContainer");
            sourceContainer.click();
            obj_page.keyboard().press("Control+A");
            obj_page.keyboard().press("Control+C");

            Locator targetContainer = obj_page.locator("#targetContainer");
            targetContainer.click();
            obj_page.keyboard().press("Control+V");

            obj_browser.close();







        }
    }
}
