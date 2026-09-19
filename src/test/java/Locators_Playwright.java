import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Locators_Playwright {
    public static void main(String[] args) {


        String userName = "standard_user";
        String password = "secret_sauce";

        String xPath_LoginButton = "//input[@id='login-button']";
        String css_LoginButton = "#login-button";
        String id_userName = "#user-name";
        String name_userName = "[name='user-name']";
        String placeholder_userName = "Username";
        String id_password = "#password";

        try (Playwright obj_playwright = Playwright.create()) {


            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
            );

            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            // 1. Navigate to the Sauce Demo login page
            obj_page.navigate("https://www.saucedemo.com/");
            try {

                Locator userById = obj_page.locator(id_userName);


                Locator userByName = obj_page.locator(name_userName);


                Locator userByPlaceholder = obj_page.getByPlaceholder(placeholder_userName);


                Locator passById = obj_page.locator(id_password);


                if (userById.isVisible() && userByName.isVisible() && userByPlaceholder.isVisible() && passById.isVisible()) {
                    System.out.println("All individual locators were identified and are visible on the page.");
                } else {
                    System.out.println("One or more locators failed to appear.");
                }
            } catch (Exception e) {
                System.out.println("Error validating initial locators: " + e.getMessage());
            }

            Locator usernameField = obj_page.getByPlaceholder(placeholder_userName);
            usernameField.fill(userName);

            Locator passwordField = obj_page.getByPlaceholder("Password");
            passwordField.fill(password);

            Locator loginButton = obj_page.locator(css_LoginButton);
            loginButton.click();


            Locator inventoryTitle = obj_page.locator(".title");
            if (inventoryTitle.isVisible() && "Products".equalsIgnoreCase(inventoryTitle.innerText())) {
                System.out.println("Login Successful! Landed on inventory page: " + obj_page.url());
            }

            obj_page.waitForTimeout(2000);

            obj_page.close();
            obj_context.close();
            obj_browser.close();
        } catch (Exception e) {
            System.out.println("Test Failed with an Exception");
            e.printStackTrace();
        }
    }
}