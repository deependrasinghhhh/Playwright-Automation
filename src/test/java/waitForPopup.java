import com.microsoft.playwright.*;

public class waitForPopup {

    public static void main(String[] args) {

        try (Playwright obj_Playwright = Playwright.create()) {

            Browser obj_Browser = obj_Playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_Context = obj_Browser.newContext();

            Page obj_Page = obj_Context.newPage();
            obj_Page.navigate("file:///c%3A/Users/CCST/Desktop/Playwright/PlaywrightMaterial/login.html");

            Locator obj_username = obj_Page.locator("#username");
            obj_username.fill("admin");

            Locator obj_password = obj_Page.locator("#password");
            obj_password.fill("admin");

            Locator obj_login = obj_Page.getByText("Sign In");

            Page obj_newPage = obj_Page.waitForPopup(() -> {
                obj_login.click();
            });

            System.out.println("New URL: " + obj_newPage.url());

            if (obj_newPage.url().contains("ControlsPractice.html")) {
                System.out.println("New page entered.");
            }

            obj_Browser.close();
        }
    }
}