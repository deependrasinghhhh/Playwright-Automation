import com.microsoft.playwright.*;

public class LocatorFiltering_Controlsprac {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {
            Browser obj_browser = obj_playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.onDialog(Dialog::accept);

            obj_page.navigate("file:///c%3A/Users/CCST/Desktop/Playwright/PlaywrightMaterial/ControlsPractice.html");
            obj_page.waitForLoadState();

            Locator dropdown = obj_page.locator("#module");


            Locator singleOption = dropdown.locator("option")
                    .filter(new Locator.FilterOptions().setHasText("CCST"));

            String value = singleOption.getAttribute("value");
            System.out.println("Extracted value: " + value);
            dropdown.selectOption(value);


            int marks = 99;

            Locator allRows = obj_page.locator("#studentTableBody tr");

            Locator validRows = allRows.filter(new Locator.FilterOptions()
                    .setHas(obj_page.locator("input[type='number']")));


            int totalRows = validRows.count();
            System.out.println("Populating marks for " + totalRows + " rows");

            for (int i = 0; i < totalRows; i++) {
                validRows.nth(i).locator("input[type='number']").fill(Integer.toString(marks));
            }

            obj_page.locator("#saveButton").click();

            obj_page.waitForTimeout(2000);
            obj_browser.close();
        } catch (Exception e) {
            System.out.println("Test Failed with an Exception");
            e.printStackTrace();
        }
    }
}