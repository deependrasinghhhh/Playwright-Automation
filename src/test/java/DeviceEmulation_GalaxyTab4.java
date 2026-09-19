import com.microsoft.playwright.*;

public class DeviceEmulation_GalaxyTab4 {

    static final String LOGIN_URL = "file:///c%3A/Users/CCST/Desktop/Playwright/PlaywrightMaterial/login.html";

    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false));
            ;

            BrowserContext obj_context = obj_browser.newContext(new Browser.NewContextOptions()
                    .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Safari/605.1.15")
                    .setViewportSize(1280, 720)
                    .setDeviceScaleFactor(2)
                    .setIsMobile(false)
                    .setHasTouch(false));

            Page obj_page = obj_context.newPage();

            Thread.sleep(2000);
            obj_page.navigate(LOGIN_URL);
            obj_page.locator("[data-testid='username-input']").fill("validUser");
            obj_page.locator("[data-testid='password-input']").fill("validPassword");


            Page newPage = obj_page.waitForPopup(() -> {
                obj_page.locator("[data-testid='submit-btn']").click();
            });

            Thread.sleep(2000);
            newPage.waitForLoadState();

            String url = newPage.url();
            System.out.println("Navigated to : " + url);
            if (!url.contains("ControlsPractice.html")) {
                throw new AssertionError("Navigation Failed:" + url);
            }

            String title = newPage.title();
            System.out.println("Page Title: " + title);

        }
        catch (Exception e) {
            System.out.println("Test Failed with an Exception");
            e.printStackTrace();
        }
    }
}
