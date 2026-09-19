import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Test_PlaywrightSetup {
    public static void main(String[] args) {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch();;

            BrowserContext obj_context = obj_browser.newContext();

            Page obj_page = obj_context.newPage();

            obj_page.navigate("https://www.google.com");

            System.out.println("Browser version: " + obj_browser.version());

            System.out.println("Page title: " + obj_page.title());
            System.out.println("Playwright Installation works correctly!");

            obj_page.waitForTimeout(2000);

            obj_page.close();
        }
        catch (Exception e) {
            System.out.println("Test Failed with an Exception");
            e.printStackTrace();
        }
    }
}
