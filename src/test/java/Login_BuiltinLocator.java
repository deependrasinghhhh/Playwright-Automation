import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class Login_BuiltinLocator
{
    public static void main(String[] args) {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_context = obj_browser.newContext();

            Page page = obj_context.newPage();
            page.navigate("file:///c%3A/Users/CCST/Desktop/Playwright/PlaywrightMaterial/login.html");


            page.getByLabel("Password").fill("admin");

            String paragraphText = page.getByText("Forgot your password?").textContent();
            System.out.println("Pass: Found Para" + paragraphText);

            Page obj_newpage = obj_context.waitForPage(() -> {
                page.getByRole(AriaRole.BUTTON,
                        new Page.GetByRoleOptions().setName("Sign In")).click();
            });


            obj_newpage.waitForLoadState();
            System.out.println("New Page Title: " + obj_newpage.title());

            obj_browser.close();

        }
        catch (Exception e) {
            System.out.println("Test Failed with an Exception");
            e.printStackTrace();
        }
    }
}
