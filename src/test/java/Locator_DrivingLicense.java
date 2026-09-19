import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

public class Locator_DrivingLicense {
    public static void main(String[] args) {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false));;

            BrowserContext obj_context = obj_browser.newContext();

            Page obj_page1 = obj_context.newPage();
            obj_page1.navigate("file:///c%3A/Users/CCST/Downloads/New%20folder%20%282%29/SeleniumMaterial/TestcasesClassAssignment-drivingLicenseUI.html");

            Locator licenseDropdown = obj_page1.locator("#licenseType");
            licenseDropdown.selectOption("permanent");


            Locator fullnameField = obj_page1.locator("#fullname");
            fullnameField.fill("Abhi Pawar");

            Locator addressField = obj_page1.locator("#address");
            addressField.fill("123 Pashan, Pune");

            Locator ageField = obj_page1.locator("#age");
            ageField.fill("25");

            Locator birthField = obj_page1.locator("#placeofbirth");
            birthField.fill("Mumbai");


            Locator maleRadio = obj_page1.locator("#Male");
            maleRadio.check();

            Locator colorblindCheckbox = obj_page1.locator("input[name='color_no']");
            colorblindCheckbox.check();


            Locator submitButton = obj_page1.locator("button[type='submit']");

            Page obj_newpage = obj_context.waitForPage(() -> {
                submitButton.click();
            });


            obj_newpage.waitForLoadState();
            System.out.println("New Page Title: " + obj_newpage.title());

            obj_page1.waitForTimeout(2000);


        }
        catch (Exception e) {
            System.out.println("Test Failed with an Exception");
            e.printStackTrace();
        }
    }
}
