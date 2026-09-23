import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class JavaScriptAlerts {

    public static void main(String[] args) throws InterruptedException {

        try(Playwright playwright = Playwright.create()) {
            Browser obj_Browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            Page obj_Page = obj_Browser.newPage();
            obj_Page.navigate("file:///c%3A/Users/CCST/Downloads/New%20folder%20%282%29/SeleniumMaterial/javascriptAlerts.html");

            obj_Page.onceDialog(dialog -> {
                System.out.println("Alert text: " + dialog.message());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                dialog.accept();
            });

            System.out.println("Before Alert");
            obj_Page.click("#alertBtn");

            String alertResult = obj_Page.textContent("#alertResult");
            assertThat(obj_Page.locator("#alertResult")).not().isEmpty();

            obj_Page.onceDialog(dialog -> {
                System.out.println("Confirm text: " + dialog.message());
                dialog.accept();
            });
            obj_Page.click("#confirmBtn");

            assertThat(obj_Page.locator("#confirmResult")).not().isEmpty();

            obj_Page.onceDialog(Dialog::dismiss);
            obj_Page.click("#confirmBtn");

            String textAfterDismiss = obj_Page.textContent("#confirmResult");
            System.out.println("Confirm after dismiss: " + textAfterDismiss);
            assertThat(obj_Page.locator("#confirmResult")).containsText("Cancel");

            obj_Page.onceDialog(dialog -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                dialog.accept("CCST Student");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            obj_Page.click("#promptBtn");

            System.out.println("Prompt result after accept: " + obj_Page.textContent("#promptResult"));
            assertThat(obj_Page.locator("#promptResult")).containsText("CCST Student");

            obj_Page.onceDialog(Dialog::dismiss);
            obj_Page.click("#promptBtn");

            String promptDismissResult = obj_Page.textContent("#promptResult");
            System.out.println("Prompt after dismiss: " + promptDismissResult);
            assertThat(obj_Page.locator("#promptResult")).containsText("Prompt was dismissed.");

            Thread.sleep(2000);

        }
    }
}