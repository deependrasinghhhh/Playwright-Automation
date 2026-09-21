import com.microsoft.playwright.*;

import java.util.List;

public class LocatorFiltering_DragDrop {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("file:///c%3A/Users/CCST/Desktop/Playwright/PlaywrightMaterial/LocatorFiltering.html");

            Locator sourceContainer = page.locator("#sourceContainer");
            Locator targetContainer = page.locator("#targetContainer");


            Locator sourceItems = sourceContainer.locator(".draggable-item");
            List<Locator> allItems = sourceItems.all();
            int totalCount = allItems.size();


            for (int i = 0; i < totalCount; i++) {
                sourceItems.first().dragTo(targetContainer);
            }


            if (sourceItems.count() == 0 && targetContainer.locator(".draggable-item").count() == totalCount) {
                System.out.println("Successfully moved all " + totalCount + " items to target container!");
            } else {
                System.out.println("Failed to move all items.");
            }

            page.waitForTimeout(2000);
            browser.close();
        } catch (Exception e) {
            System.err.println("Test Failed with an Exception:");
            e.printStackTrace();
        }
    }
}