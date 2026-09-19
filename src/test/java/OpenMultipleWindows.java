import com.microsoft.playwright.*;

public class OpenMultipleWindows {
    public static void main(String[] args) {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false));;

            BrowserContext obj_context = obj_browser.newContext();

            Page obj_page1 = obj_context.newPage();
            obj_page1.navigate("https://www.google.com");
            System.out.println("Page 1 title: " + obj_page1.title());

            // Second Page Object
            Page obj_page2 = obj_context.newPage();
            obj_page2.navigate("https://bing.com");
            System.out.println("Page 2 title: " + obj_page2.title());

            obj_page1.waitForTimeout(2000);

            obj_page1.close();
            obj_page2.close();
        }
        catch (Exception e) {
            System.out.println("Test Failed with an Exception");
            e.printStackTrace();
        }
    }
}
