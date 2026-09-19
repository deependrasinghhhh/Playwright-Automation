import com.microsoft.playwright.*;

import java.util.Map;

public class GeoLocation {

    static final String LOGIN_URL = "file:///c%3A/Users/CCST/Desktop/Playwright/PlaywrightMaterial/login.html";

    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false));
            ;

            BrowserContext obj_context = obj_browser.newContext(new Browser.NewContextOptions()
                    .setGeolocation(new com.microsoft.playwright.options.Geolocation(48.8566, 2.3522))
                    .setPermissions(java.util.Arrays.asList("geolocation")));

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

            // Corrected JavaScript string structure evaluated on newPage
            Map<String, Object> location = (Map<String, Object>) newPage.evaluate(
                    "() => new Promise((resolve, reject) => {" +
                            "  navigator.geolocation.getCurrentPosition(" +
                            "    position => resolve({" +
                            "      latitude: position.coords.latitude," +
                            "      longitude: position.coords.longitude" +
                            "    })," +
                            "    error => reject(error.message)" +
                            "  );" +
                            "})"
            );

            double actualLat = (double) location.get("latitude");
            double actualLon = (double) location.get("longitude");

            System.out.println("Latitude: " + actualLat + " Longitude: "+ actualLon);

            obj_browser.close();

        }
        catch (Exception e) {
            System.out.println("Test Failed with an Exception");
            e.printStackTrace();
        }
    }
}
