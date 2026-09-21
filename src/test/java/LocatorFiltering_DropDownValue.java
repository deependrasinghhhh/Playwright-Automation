import com.microsoft.playwright.*;

public class LocatorFiltering_DropDownValue {

    public static void main(String[] args) {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false));
            ;

            BrowserContext obj_context = obj_browser.newContext(new Browser.NewContextOptions().setLocale("ja-JP").setTimezoneId("Australia/Sydney"));

            Page obj_page = obj_context.newPage();

            obj_page.navigate("https://www.wikipedia.org/");
            obj_page.waitForTimeout(10000);

            System.out.println("Page title: " + obj_page.title());

            Locator obj_langLabel = obj_page.locator("#jsLangLabel");
            String langValue = obj_langLabel.textContent();
            System.out.println("Language: " + langValue);


            Locator obj_tzLabel = obj_page.locator("");
            String tzValue = obj_tzLabel.textContent();
            System.out.println("Timezone from page: " + tzValue);


            obj_browser.close();

        }
        catch (Exception e) {
        e.printStackTrace();}
    }
}
