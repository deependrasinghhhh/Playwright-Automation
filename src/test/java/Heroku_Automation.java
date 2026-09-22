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
                System.out.println("Delete button disappeared");


            }

            obj_Page.navigate("https://the-internet.herokuapp.com/");

            Locator obj_DragDrop = obj_Page.getByText("Drag and Drop");
            obj_DragDrop.click();

            Locator column_a = obj_Page.locator("#column-a");
            Locator column_b = obj_Page.locator("#column-b");
            column_a.dragTo(column_b);


            if (column_a.locator("header").innerText().equals("B")) {
                System.out.println("Drag Success");
            }

            obj_Page.navigate("https://the-internet.herokuapp.com/");

            Locator obj_DropDown = obj_Page.getByText("Dropdown");
            obj_DropDown.click();

            if (obj_Page.url().contains("dropdown")) {
                System.out.println("Page opened");

            }

            Locator obj_List = obj_Page.locator("#dropdown");
            obj_List.selectOption("1");

            String validate = obj_List.locator("option:checked").innerText();
            System.out.println("Text is: " + validate);


            obj_Page.navigate("https://the-internet.herokuapp.com/");

            Locator obj_DynamicCtrl = obj_Page.getByText("Dynamic Controls");
            obj_DynamicCtrl.click();

            Locator obj_Checkbox = obj_Page.locator("#checkbox");
            obj_Checkbox.click();

            Locator obj_RemoveBtn = obj_Page.locator("//button[@onclick = 'swapCheckbox()']");
            obj_RemoveBtn.click();

            Locator obj_message = obj_Page.locator("#message");





            Thread.sleep(3000);

            obj_Browser.close();


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
