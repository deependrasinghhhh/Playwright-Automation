import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SauceDemoLoginTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            Page page = browser.newPage();

            page.navigate("https://www.saucedemo.com/");

            Locator usernameInput = page.locator("#user-name");
            Locator passwordInput = page.locator("#password");
            Locator loginButton = page.locator("#login-button");
            Locator errorContainer = page.locator(".error-message-container");


            CustomAssertions.assertThat(loginButton).hasCssClass("submit-button");
            System.out.println("Login button class verified!");


            loginButton.click();

            CustomAssertions.assertThat(errorContainer)
                    .hasCssClass("error")
                    .hasText("Epic sadface: Username is required");
            System.out.println("Error message text verified!");


            usernameInput.fill("standard_user");
            passwordInput.fill("secret_sauce");
            loginButton.click();


            Locator pageHeader = page.locator(".title");
            CustomAssertions.assertThat(pageHeader).hasText("Products");
            System.out.println("Dashboard title verified!");
        }
    }
}