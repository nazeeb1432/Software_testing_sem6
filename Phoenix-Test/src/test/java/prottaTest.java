// Generated by Selenium IDE
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class prottaTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    // Force specific GeckoDriver version compatible with Firefox ESR
    WebDriverManager.firefoxdriver().driverVersion("0.35.0").setup();

    // Configure Firefox options to use ESR explicitly
    FirefoxOptions options = new FirefoxOptions();
    options.addArguments("--headless");
    options.setBinary("/usr/bin/firefox-esr");

    driver = new FirefoxDriver(options);
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void test2() {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      driver.get("http://localhost:4000/sign_in");
      driver.manage().window().setSize(new Dimension(992, 1090));
      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
      // Replace with correct locator if needed
      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".add-new > .inner"))).click();
      wait.until(ExpectedConditions.elementToBeClickable(By.id("board_name"))).click();
      driver.findElement(By.id("board_name")).sendKeys("protta");
      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".inner"))).click();
      driver.findElement(By.id("list_name")).sendKeys("cp");
      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".add-new > .inner"))).click();
      driver.findElement(By.id("list_name")).sendKeys("nerd");
      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
      assertThat(driver.findElement(By.cssSelector("h3")).getText(), is("protta"));
    } catch (Exception e) {
      System.err.println("Test failed: " + e.getMessage());
      fail("Test failed: " + e.getMessage());
    }
  }
}
