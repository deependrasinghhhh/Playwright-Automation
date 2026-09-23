import com.microsoft.playwright.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class challenge_MouseKeyboardActions {

    public static void main(String[] args) {

        try(Playwright obj_Playwright = Playwright.create()){

            Browser obj_browser = obj_Playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_context = obj_browser.newContext();

            Page obj_page = obj_context.newPage();
            obj_page.navigate("file:///c%3A/Users/CCST/Downloads/New%20folder%20%282%29/SeleniumMaterial/challenge_MouseKeyboardActions.html");

            Locator documentsMenu = obj_page.locator("#documentsMenu");
            documentsMenu.hover();

            Locator uploadDocsLink = obj_page.locator("#uploadDocLink");
            uploadDocsLink.click();

            Locator scroll = obj_page.locator(".spacer");
            scroll.scrollIntoViewIfNeeded();

            //validate
            assertThat(scroll).isVisible();

            Path filePath = Paths.get("C:\\Users\\CCST\\Desktop\\Assignment7.docx");

            Locator fileInput = obj_page.locator("#fileInput");
            fileInput.setInputFiles(filePath);

            Locator selectedFileName = obj_page.locator("#fileName");
            assertThat(selectedFileName).hasText("Selected file: Assignment7.docx");


            Locator uploadBtn = obj_page.locator("#uploadBtn");
            uploadBtn.click();

            Locator fileResult = obj_page.locator("#result");
            assertThat(fileResult).hasText("You greedy fellow !!");

            System.out.println("Result text: " + fileResult.textContent());

            obj_browser.close();

        }
    }
}
