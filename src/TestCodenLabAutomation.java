import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCodenLabAutomation {
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	Actions action = new Actions(driver);
	String WebSite = "https://codenboxautomationlab.com/practice/";

	@BeforeTest
	public void setUp() {
		driver.get(WebSite);
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void RadioBtn() {
		WebElement RadionBtnn = driver.findElement(By.id("radio-btn-example"));
		int RadioLength = RadionBtnn.findElements(By.xpath("//input[@name='radioButton']")).size(); // you can find it
																									// using TagName
																									// "input"
		int RandomRadio = rand.nextInt(RadioLength); // same as the below
//		int RandomRadio = rand.nextInt(RadionBtnn.findElements(By.xpath("//input[@name='radioButton']")).size();
		WebElement SelectedRadioBtn = RadionBtnn.findElements(By.xpath("//input[@name='radioButton']")).get(RandomRadio);
		SelectedRadioBtn.click();
		Boolean ActualResult = SelectedRadioBtn.isSelected();
		Boolean ExpectedResult = true;
		Assert.assertEquals(ActualResult, ExpectedResult);
	}

	@Test(priority = 2)
	public void DynamicInput() throws InterruptedException {
		String[] Countries = { "ER", "AI", "PO", "EW", "AZ", "AS", "TR", "HA" };
		int RandomCountry = rand.nextInt(Countries.length);
		String SendInput = Countries[RandomCountry];
		WebElement CountryInput = driver.findElement(By.id("autocomplete"));
		CountryInput.sendKeys(SendInput);
		Thread.sleep(2000);
		CountryInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String ReturningData = (String) js.executeScript("return arguments [0].value", CountryInput);
		String UpdatedResult = ReturningData.toLowerCase();
		boolean ActualResult = UpdatedResult.contains(SendInput.toLowerCase());
		Assert.assertEquals(ActualResult, true);

	}

	@Test(priority = 3)
	public void SelectList() {
		WebElement SelectList = driver.findElement(By.id("dropdown-class-example"));
		Select selector = new Select(SelectList);
		int RandomSelect = rand.nextInt(1, 4);
		selector.selectByIndex(RandomSelect);
		// You Can Do it Using "Select By Visisble Text" Or "Select By Value".
		
	}

	@Test(priority = 4)
	public void CheckBoxExamples() {
		WebElement CheckBoxList = driver.findElement(By.id("checkbox-example"));
		int CheckBoxSize = CheckBoxList.findElements(By.xpath("//input[@type='checkbox']")).size();
		int RandomCheckBox = rand.nextInt(CheckBoxSize);
		CheckBoxList.findElements(By.xpath("//input[@type='checkbox']")).get(RandomCheckBox).click();
	}
}
