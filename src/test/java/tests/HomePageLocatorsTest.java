package tests;

import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import util.Base;

import java.util.List;

public class HomePageLocatorsTest extends Base {

  @Test(description = "locate History categories")
  public void testHistoryCategoryIsLeftOfFineArtsAandBelowScience() {
   driver.findElement(By.xpath("//img[@alt='Science'][1]")).click();
   WebElement theLearningBrain = driver.findElement(By.xpath("//img[@alt='The Learning Brain']"));
   WebElement understanding = driver.findElement(By.xpath("//img[@alt='Understanding the Quantum World']"));
   driver.findElement(withTagName("li").toLeftOf(understanding).above(theLearningBrain)).click();
  }
}
