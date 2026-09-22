import com.microsoft.playwright.*;

public class Heroku_Automation {

    public static void main(String[] args) {

        try (Playwright obj_Playwright = Playwright.create()) {

            Browser obj_Browser = obj_Playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_Context = obj_Browser.newContext();

            Page obj_Page = obj_Context.newPage();
            obj_Page.navigate("https://the-internet.herokuapp.com/");

            Locator add_remove = obj_Page.locator("//a[@href = '/add_remove_elements/']");
            add_remove.click();

            obj_Page.url();
            if (obj_Page.url().contains("/add_remove_elements/")) {
                System.out.println("Entered new Page");
            }


            Locator add_Element = obj_Page.getByText("Add Element");
            add_Element.click();

            if (obj_Page.isVisible(".added-manually")) {
                System.out.println("Delete button is visible");

            }


            Locator obj_Delete = obj_Page.locator(".added-manually");
            obj_Delete.click();


            if (!obj_Page.isVisible(".added-manually")) {
                System.out.println("Button disappeared");


            }

            Thread.sleep(3000);


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
