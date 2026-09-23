import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.concurrent.atomic.AtomicInteger;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AlertsWithOnDialog {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {
            Browser obj_browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page obj_page = obj_browser.newPage();
            obj_page.navigate("file:///c%3A/Users/CCST/Downloads/New%20folder%20%282%29/SeleniumMaterial/javascriptAlerts.html");

            AtomicInteger confirmCount = new AtomicInteger(0);
            AtomicInteger promptCount = new AtomicInteger(0);

            obj_page.onDialog(dialog -> {
                System.out.println("Dialog type: " + dialog.type() + "message: " + dialog.message());

                switch (dialog.type()) {
                    case "alert":
                        dialog.accept();
                        break;

                    case "confirm":
                        if (confirmCount.getAndIncrement() == 0) {
                            dialog.accept();
                        } else {
                            dialog.dismiss();
                        }
                        break;

                    case "prompt":
                        if (promptCount.getAndIncrement() == 0) {
                            dialog.accept("CCST Student");
                        } else {
                            dialog.dismiss();
                        }
                        break;

                    default:
                        dialog.dismiss();
                }
            });

            obj_page.click("#alertBtn");
            obj_page.waitForTimeout(500);

            String alertResult = obj_page.textContent("#alertResult");
            assertThat(obj_page.locator("#alertResult")).not().isEmpty();

            obj_page.click("#confirmBtn");
            obj_page.waitForTimeout(500);

            assertThat(obj_page.locator("#confirmResult")).not().isEmpty();

            obj_page.click("#confirmBtn");

            String textAfterDismiss = obj_page.textContent("#confirmResult");
            System.out.println("Confirm after dismiss: " + textAfterDismiss);
            assertThat(obj_page.locator("#confirmResult")).containsText("Cancel");

            obj_page.click("#promptBtn");

            System.out.println("Prompt result after accept: " + obj_page.textContent("#promptResult"));
            assertThat(obj_page.locator("#promptResult")).containsText("CCST Student");

            obj_page.click("#promptBtn");
            obj_page.waitForTimeout(500);

            String promptDismissResult = obj_page.textContent("#promptResult");
            System.out.println("Prompt after dismiss: " + promptDismissResult);
            assertThat(obj_page.locator("#promptResult")).containsText("Prompt was dismissed.");

            obj_browser.close();

        }
    }
}