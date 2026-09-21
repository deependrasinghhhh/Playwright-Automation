import com.microsoft.playwright.*;
import java.util.List;

public class LocatorChaining_ControlsPrac {

    static final String LOGIN_URL = "file:///c%3A/Users/CCST/Desktop/Playwright/PlaywrightMaterial/ControlsPractice.html";

    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));

            BrowserContext obj_context = obj_browser.newContext();
            Page obj_controlsPage = obj_context.newPage();

            obj_controlsPage.onDialog(dialog -> {
                System.out.println("Alert displayed: " + dialog.message());
                dialog.accept();
            });

            obj_controlsPage.navigate(LOGIN_URL);

            Locator modulesDropdown = obj_controlsPage.locator("#module");
            modulesDropdown.click();

            List<String> optionTexts = modulesDropdown.locator("option").allInnerTexts();
            System.out.println("Dropdown options found: " + optionTexts);

            modulesDropdown.selectOption("CCST");

            Locator rows = obj_controlsPage.locator("table tbody tr");
            int rowCount = rows.count();
            System.out.println("Row Count: " + rowCount);


            for (int i = 0; i < rowCount; i++) {
                Locator row = rows.nth(i);
                Locator marksInput = row.locator("input[type='number']");
                marksInput.fill("99");
            }

            Locator saveButton = obj_controlsPage.locator("#saveButton");
            System.out.println("Save button enabled: " + saveButton.isEnabled());


            saveButton.click();

            obj_controlsPage.waitForTimeout(2000);

            obj_context.close();
            obj_browser.close();
        } catch (Exception e) {
            System.out.println("Test Failed with an Exception");
            e.printStackTrace();
        }
    }
}