import com.microsoft.playwright.Locator;

public class CustomAssertions {

    private final Locator locator;

    private CustomAssertions(Locator locator) {
        this.locator = locator;
    }

    public static CustomAssertions assertThat(Locator locator) {
        return new CustomAssertions(locator);
    }

    // 1. Assert CSS class
    public CustomAssertions hasCssClass(String cssClass) {
        String actualClass = locator.getAttribute("class");
        System.out.println("In Custom Assertion (hasCssClass)");
        System.out.println("Locator: " + locator.toString());

        if (actualClass == null || !actualClass.contains(cssClass)) {
            throw new AssertionError(
                    "Expected element to have class '" + cssClass + "' but found: '" + actualClass + "'"
            );
        }

        return this;
    }


    public CustomAssertions hasText(String expectedText) {
        String actualText = locator.innerText();
        System.out.println("In Custom Assertion (hasText)");
        System.out.println("Locator: " + locator.toString());

        if (actualText == null || !actualText.trim().contains(expectedText)) {
            throw new AssertionError(
                    "Expected element to contain text '" + expectedText + "' but found: '" + actualText + "'"
            );
        }

        return this;
    }



}