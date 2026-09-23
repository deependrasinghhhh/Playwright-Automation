import com.microsoft.playwright.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class FileDownload {

    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_context = obj_browser.newContext(new Browser.NewContextOptions().setAcceptDownloads(true));

            Page obj_page = obj_context.newPage();
            obj_page.navigate("file:///c%3A/Users/CCST/Downloads/New%20folder%20%282%29/SeleniumMaterial/fileDownload.html");

            try {
                Download obj_download = obj_page.waitForDownload(() -> {
                    obj_page.click("#downloadBtn");
                });

                System.out.println("Download started. Suggested filename: " + obj_download.suggestedFilename());

                Path saveDir = Paths.get("C:\\Users\\CCST\\Downloads");

                Path savePath = saveDir.resolve(obj_download.suggestedFilename());
                obj_download.saveAs(savePath);

                System.out.println("File saved to: " + savePath.toAbsolutePath());

                assertThat(Files.exists(savePath)).isTrue();

                String fileContent = Files.readString(savePath);
                System.out.println("File content: " + fileContent);
                assertThat(fileContent).isNotEmpty();


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                obj_page.close();
            }

        }
    }
}
