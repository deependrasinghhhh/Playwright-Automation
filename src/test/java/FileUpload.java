import com.microsoft.playwright.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FileUpload {

    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_Context = obj_browser.newContext();

            Page obj_page = obj_Context.newPage();
            obj_page.navigate("file:///c%3A/Users/CCST/Downloads/New%20folder%20%282%29/SeleniumMaterial/fileUpload.html");

            Path filePath = Paths.get("C:\\Users\\CCST\\Desktop\\Assignment7.docx");

            Locator fileInput = obj_page.locator("#fileInput");
            fileInput.setInputFiles(filePath);

            Locator selectedFileName = obj_page.locator("#fileName");
            assertThat(selectedFileName).hasText("Selected file: Assignment7.docx");

            Locator uploadBtn = obj_page.locator("#uploadBtn");
            uploadBtn.click();

            Locator fileResult = obj_page.locator("#result");
            assertThat(fileResult).hasText("File 'Assignment7.docx' uploaded successfully!");

            System.out.println("Result text: " + fileResult.textContent());

            obj_browser.close();

        }
    }
}