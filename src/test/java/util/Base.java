package util;


import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class Base {

  protected static WebDriver driver;
  @Parameters({"browser" })
  @BeforeClass
  public static void setUpClass(@Optional("chrome") String browser) {
    if (browser.equals("chrome")){
      WebDriverManager.chromedriver().setup();
    }else if (browser.equals("firefox")){
      WebDriverManager.firefoxdriver().setup();
    }else if (browser.equals("ie")){
      WebDriverManager.iedriver().setup();
    }else if (browser.equals("edge")){
      WebDriverManager.edgedriver().setup();
    }else {
      WebDriverManager.chromedriver().setup();
    }

  }

  @BeforeMethod(alwaysRun = true)
  public void setupTest() throws InterruptedException {

    driver = new ChromeDriver();
    driver.get("https://www.thegreatcourses.com/");
    driver.manage().window().maximize();
    Thread.sleep(2000);
  }

  @AfterMethod
  public void tearDownTest(ITestResult result) {

    if (result.getStatus() == ITestResult.SUCCESS) {
      System.out.println((result.getMethod().getMethodName() + " passed"));
    } else if (result.getStatus() == ITestResult.FAILURE) {
      System.out.println(("Error: Test Failed..."));
      System.out.println(result.getMethod().getMethodName() + " failed");
    } else if (result.getStatus() == ITestResult.SKIP) {
      System.out.println(result.getMethod().getMethodName() + " skipped");
    } else {
      System.out.println("Error: Test Failed...");
    }
    driver.close();
  }

  @AfterClass
  public void tearDownClass() {
    System.out.println("After all test completed...");
    if (driver != null) {
      driver.quit();
    }
  }
}
