import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class LocatorFiltering_DropDownValue {
    private Playwright obj_playwright;
    private Browser obj_browser;
    private BrowserContext obj_context;
    private Page obj_page;

    @BeforeMethod
    public void setUp() {
        obj_playwright = Playwright.create();
        obj_browser = obj_playwright.chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));

        obj_context = obj_browser.newContext(
                new Browser.NewContextOptions()
                        .setLocale("ja-JP")
                        .setTimezoneId("Australia/Sydney")
                        .setRecordVideoSize(1280, 720)
                        .setRecordVideoDir(Paths.get("videos/"))
        );

        // Creates and assigns the Page instance
        obj_page = obj_context.newPage();
    }

    @Test
    public void testWikipediaLanguageAndContext() {
        obj_page.navigate("https://www.wikipedia.org/");

        System.out.println("Page title: " + obj_page.title());

        Locator obj_langLabel = obj_page.locator("#jsLangLabel");
        String langValue = obj_langLabel.textContent();
        System.out.println("Language: " + langValue);

        assertThat(obj_langLabel).hasText("ja");

        String tzValue = (String) obj_page.evaluate("() => Intl.DateTimeFormat().resolvedOptions().timeZone");
        System.out.println("Timezone from page environment: " + tzValue);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        Video video = (obj_page != null) ? obj_page.video() : null;

        // The context must be closed before reading or deleting the video file
        if (obj_context != null) {
            obj_context.close();
        }

        if (video != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                System.out.println("Test failed - keeping video: " + video.path());
            } else {
                try {
                    Files.deleteIfExists(video.path());
                } catch (Exception e) {
                    System.out.println("Could not delete video: " + e.getMessage());
                }
            }
        }

        if (obj_browser != null) {
            obj_browser.close();
        }
        if (obj_playwright != null) {
            obj_playwright.close();
        }
    }
}