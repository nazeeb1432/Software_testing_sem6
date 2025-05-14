import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By initialSignInButton = By.cssSelector("button");
    private final By passwordInput = By.id("user_password");
    private final By submitButton = By.cssSelector("button");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void navigateToSignIn() {
        driver.get("http://localhost:4000/sign_in");
    }

    public LoginPage clickInitialSignInButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(initialSignInButton));
        button.click();
        return this;
    }

    public boolean isPasswordFieldPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
            return true;
        } catch (Exception e) {
            System.err.println("Password field not found: " + e.getMessage());
            return false;
        }
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        wait.until(ExpectedConditions.elementToBeClickable(passwordField)).click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickSubmitButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        button.click();
    }
}