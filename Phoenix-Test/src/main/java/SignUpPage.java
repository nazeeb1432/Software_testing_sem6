import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;

public class SignUpPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By createAccountLink = By.linkText("Create new account");
    private final By firstNameInput = By.id("user_first_name");
    private final By lastNameInput = By.id("user_last_name");
    private final By emailInput = By.id("user_email");
    private final By passwordInput = By.id("user_password");
    private final By passwordConfirmationInput = By.id("user_password_confirmation");
    private final By submitButton = By.cssSelector("button");
    private final By formContainer = By.cssSelector("form");

    public SignUpPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void navigateToSignIn() {
        driver.get("http://localhost:4000/sign_in");
    }

    public SignUpPage clickCreateAccountLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(createAccountLink));
        link.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(formContainer));
        return this;
    }

    public boolean isSignUpFormPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
            return true;
        } catch (Exception e) {
            System.err.println("Sign-up form not found: " + e.getMessage());
            logPageSource();
            return false;
        }
    }

    public void enterFirstName(String firstName) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        wait.until(ExpectedConditions.elementToBeClickable(field)).click();
        field.clear();
        field.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        wait.until(ExpectedConditions.elementToBeClickable(field)).click();
        field.clear();
        field.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        wait.until(ExpectedConditions.elementToBeClickable(field)).click();
        field.clear();
        field.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        wait.until(ExpectedConditions.elementToBeClickable(field)).click();
        field.clear();
        field.sendKeys(password);
    }

    public void enterPasswordConfirmation(String passwordConfirmation) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordConfirmationInput));
        wait.until(ExpectedConditions.elementToBeClickable(field)).click();
        field.clear();
        field.sendKeys(passwordConfirmation);
    }

    public void clickSubmitButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        button.click();
    }

    private void logPageSource() {
        try {
            String pageSource = driver.getPageSource();
            try (FileWriter writer = new FileWriter("signup_page_source.html")) {
                writer.write(pageSource);
            }
            System.err.println("Page source saved to signup_page_source.html for debugging");
        } catch (IOException e) {
            System.err.println("Failed to save page source: " + e.getMessage());
        }
    }
}