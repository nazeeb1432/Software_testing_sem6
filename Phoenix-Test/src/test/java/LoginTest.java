import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void setUp() {
    driver = new ChromeDriver();
    driver.manage().window().setSize(new Dimension(992, 1090));
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void login() {
    try {
      LoginPage loginPage = new LoginPage(driver, wait);
      loginPage.navigateToSignIn();
      loginPage.clickInitialSignInButton();

      if (!loginPage.isPasswordFieldPresent()) {
        throw new RuntimeException("Password field with id='user_password' not found on the page.");
      }

      loginPage.enterPassword("1234");
      loginPage.clickSubmitButton();
    } catch (Exception e) {
      System.err.println("Test failed: " + e.getMessage());
      throw e;
    }
  }
}