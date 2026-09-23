import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class actionClass_Hover {

    public static void main(String[] args) {

        try(Playwright obj_Playwright = Playwright.create()) {

            Browser obj_browser = obj_Playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_context = obj_browser.newContext();

            Page obj_page = obj_context.newPage();
            obj_page.navigate("file:///c%3A/Users/CCST/Downloads/New%20folder%20%282%29/SeleniumMaterial/actionClass_Menu.html#");

            Locator obj_product = obj_page.locator("#productsMenu");
            obj_product.hover();

            Locator obj_Laptop = obj_page.locator("#laptopsLink");
            obj_Laptop.click();

            Locator obj_Result = obj_page.locator("#result");
            assertThat(obj_Result).isVisible();
            System.out.println(obj_Result.textContent());

            obj_product.hover();

            Locator obj_Phone = obj_page.locator("#phonesLink");
            obj_Phone.click();

            assertThat(obj_Result).isVisible();
            System.out.println(obj_Result.textContent());

            Locator obj_Tablets = obj_page.locator("#tabletsLink");
            obj_Tablets.click();

            assertThat(obj_Result).isVisible();
            System.out.println(obj_Result.textContent());

            obj_browser.close();

        }
    }
}