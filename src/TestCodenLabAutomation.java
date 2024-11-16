import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
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
		WebElement SelectedRadioBtn = RadionBtnn.findElements(By.xpath("//input[@name='radioButton']"))
				.get(RandomRadio);
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
		String ReturningData = (String) js.executeScript("return arguments[0].value", CountryInput);
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
		// Other Way to click on all of them using for loop.
//		WebElement CheckBoxList = driver.findElement(By.id("checkbox-example"));
//		List <WebElement> CheckBox = CheckBoxList.findElements(By.xpath("//input[@type='checkbox']"));
//		for (int i = 0; i < CheckBox.size(); i++) {
//			CheckBox.get(i).click();
	}

	@Test(priority = 5)
	public void SwitchWindow() throws InterruptedException {
		driver.findElement(By.id("openwindow")).click();
		Thread.sleep(1000);
		List<String> WindowHandles = new ArrayList<>(driver.getWindowHandles());
		// get (1) means the second page.
		driver.switchTo().window(WindowHandles.get(1));
		Thread.sleep(1000);
		driver.findElement(By.id("menu-item-9675")).click();
		Thread.sleep(5000);
		// if you want to go back for the main page we can use the same code but 0
		// instead of 1.
		driver.switchTo().window(WindowHandles.get(0));
		Thread.sleep(3000);
	}

	@Test(priority = 6)
	public void TabWindow() throws InterruptedException {
		WebElement OpenTab = driver.findElement(By.id("opentab"));
		OpenTab.click();
		List<String> WindowHandles = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(WindowHandles.get(1));
		Thread.sleep(1000);
		String Actual = driver.getTitle();
		String Expected = "Codenbox";
		Assert.assertTrue(Actual.contains(Expected), "Actual title does not contain the expected text");
		Thread.sleep(1000);
		driver.switchTo().window(WindowHandles.get(0));
		Thread.sleep(1000);
	}

	@Test(priority = 7)
	public void AlertExample() throws InterruptedException {
		// Normal Alert.
		WebElement AlertBtn = driver.findElement(By.id("alertbtn"));
		AlertBtn.click();
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		// Confirm Alert.
		WebElement Name = driver.findElement(By.id("name"));
		Name.sendKeys("Omar");
		WebElement ConfirmAlertBtn = driver.findElement(By.id("confirmbtn"));
		ConfirmAlertBtn.click();
		boolean ActualAssert = driver.switchTo().alert().getText().contains("Omar");
		boolean ExpectedAssert = true;
		Assert.assertEquals(ActualAssert, ExpectedAssert);
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
	}

	@Test(priority = 8)
	public void TableExample() {
		WebElement Table = driver.findElement(By.id("product"));
		List<WebElement> TableSize = Table.findElements(By.tagName("td"));
		for (int i = 1; i < TableSize.size(); i += 3) {
			String cellText = TableSize.get(i).getText();
			System.out.println(cellText);
		}
	}

	@Test(priority = 9)
	public void ElementDisplayed() throws InterruptedException {
		// Hide
		WebElement HideBtn = driver.findElement(By.id("hide-textbox"));
		HideBtn.click();
		boolean ActualAssert = driver.findElement(By.id("displayed-text")).isDisplayed();
		boolean ExpectedAssert = false;
		Assert.assertEquals(ActualAssert, ExpectedAssert);
		Thread.sleep(2000);
		// Enable
		WebElement EnableBtn = driver.findElement(By.id("show-textbox"));
		EnableBtn.click();
		boolean ActualAssert2 = driver.findElement(By.id("displayed-text")).isDisplayed();
		boolean ExpectedAssert2 = true;
		Assert.assertEquals(ActualAssert2, ExpectedAssert2);
	}

	@Test(priority = 10)
	public void Enable_Disable() throws InterruptedException {
		// Disable
		WebElement DisableInput = driver.findElement(By.id("disabled-button"));
		DisableInput.click();
		WebElement CheckInput = driver.findElement(By.id("enabled-example-input"));
		boolean ActualResult = CheckInput.isEnabled();
		boolean ExpectedResult = false;
		Assert.assertEquals(ActualResult, ExpectedResult);
		Thread.sleep(1000);
		// Enable
		WebElement EnableInput = driver.findElement(By.id("enabled-button"));
		EnableInput.click();
		boolean ActualResult2 = CheckInput.isEnabled();
		boolean ExpectedResult2 = true;
		Assert.assertEquals(ActualResult2, ExpectedResult2);
	}

	@Test(priority = 11)
	public void MouseHoverTest() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,1750)");
		WebElement Hovering = driver.findElement(By.id("mousehover"));
		action.moveToElement(Hovering).perform();
	}
}
