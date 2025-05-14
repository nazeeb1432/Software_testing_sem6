import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CreateUserTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().setSize(new Dimension(992, 1090));
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void createUser() {
    try {
      SignUpPage signUpPage = new SignUpPage(driver, wait);
      signUpPage.navigateToSignIn();
      signUpPage.clickCreateAccountLink();

      if (!signUpPage.isSignUpFormPresent()) {
        throw new RuntimeException("Sign-up form not found on the page. Check signup_page_source.html for details.");
      }

      signUpPage.enterFirstName("dhrubo");
      signUpPage.enterLastName("ahmed");
      signUpPage.enterEmail("ahmed@gmail.com");
      signUpPage.enterPassword("12345");
      signUpPage.enterPasswordConfirmation("12345");
      signUpPage.clickSubmitButton();
    } catch (Exception e) {
      System.err.println("Test failed: " + e.getMessage());
      throw e;
    }
  }
}