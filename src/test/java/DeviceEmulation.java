import com.microsoft.playwright.*;

public class DeviceEmulation {

    static final String LOGIN_URL = "file:///c%3A/Users/CCST/Desktop/Playwright/PlaywrightMaterial/login.html";

    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false));
            ;

            BrowserContext obj_context = obj_browser.newContext(new Browser.NewContextOptions()
                    .setUserAgent("Mozilla/5.0 (BB10; Touch) AppleWebKit/537.10+ (KHTML, like Gecko) Version/10.0.9.2372 Mobile")
                    .setViewportSize(360, 640)
                    .setDeviceScaleFactor(2)
                    .setIsMobile(true)
                    .setHasTouch(true));

            Page obj_page = obj_context.newPage();

            Thread.sleep(2000);
            obj_page.navigate(LOGIN_URL);
            obj_page.locator("[data-testid='username-input']").fill("validUser");
            obj_page.locator("[data-testid='password-input']").fill("validPassword");

            // CLICK THE LOGIN BUTTON HERE (Adjust the selector if your testid is different)
            obj_page.locator("[data-testid='login-button']").click();

            Thread.sleep(2000);
            obj_page.waitForLoadState();

            String url = obj_page.url();
            System.out.println("Navigated to : " + url);
            if (!url.contains("ControlsPractice.html")) {
                throw new AssertionError("Navigation Failed:" + url);
            }

            String title = obj_page.title();
            System.out.println("Page Title: " + title);

        }
        catch (Exception e) {
            System.out.println("Test Failed with an Exception");
            e.printStackTrace();
        }
    }
}
