import com.microsoft.playwright.*;

public class playwright_Revision {

    public static void main(String[] args) {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_Context = obj_browser.newContext();

            Page obj_page = obj_Context.newPage();

            obj_page.navigate("https://www.saucedemo.com");

            obj_page.title();
            System.out.println("Page Title: " + obj_page.title());

            Locator obj_username = obj_page.locator("#user-name");
            obj_username.fill("standard_user");

            Locator obj_password = obj_page.locator("#password");
            obj_password.fill("secret_sauce");

            Thread.sleep(2000);

            Locator obj_login = obj_page.locator("#login-button");
            obj_login.click();

            obj_page.url();
            System.out.println("New URL: " + obj_page.url());

            if (obj_page.url().contains("inventory.html")) {
                System.out.println("Entered new page");

            }









        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
