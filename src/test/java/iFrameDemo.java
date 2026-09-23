import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class iFrameDemo {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    private static final String IFRAME_URL = "file:///c%3A/Users/CCST/Downloads/New%20folder%20%282%29/SeleniumMaterial/iFrameDemo.html";

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();

        page.navigate(IFRAME_URL);
    }

    @Test(priority = 1)
    public void testFrame1() {
        FrameLocator frame1 = page.frameLocator("iframe").nth(0);
        Locator frame1Btn = frame1.locator("#frame1Btn");

        frame1Btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        frame1Btn.click();

        assertThat(frame1Btn).isEnabled();
    }

    @Test(priority = 2)
    public void testFrame2() {
        FrameLocator frame2 = page.frameLocator("//iframe[@name='frameByName']");
        Locator frame2Input = frame2.locator("#frame2Input");

        frame2Input.fill("Playwright is amazing");

        assertThat(frame2Input).hasValue("Playwright is amazing");
    }

    @Test(priority = 3)
    public void testFrame3() {
        FrameLocator frame3 = page.frameLocator("#frame3");
        Locator dropdown = frame3.locator("#frame3Dropdown");

        dropdown.selectOption(new SelectOption().setLabel("Two"));

        assertThat(dropdown).hasValue("two");
    }

    @Test(priority = 4)
    public void testMainPageButton() {
        Locator mainBtn = page.locator("#mainBtn");

        assertThat(mainBtn).isVisible();
    }

    @AfterMethod
    public void tearDown() {
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}