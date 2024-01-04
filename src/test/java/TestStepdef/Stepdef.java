package TestStepdef;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import Configuration.TestBase;
import Locators.Objectlocators;
import Utils.ExcelUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import common.CommonMethod;
import HireProus_Page.LoginPage_Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import common.XlsReader;

@SuppressWarnings({ "deprecation", "unused" })
public class Stepdef extends TestBase {
	int row;
	XSSFSheet sheet;

	@After
	public void addScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "image");
		}
	}

	@Given("User navigate to Hireprous page")
	public void user_navigate_to_hireprous_page() throws IOException, InterruptedException {

		TestBase.env();
		driver.get(TestBase.excelTestData().getCellData("Login", "URL" , 2));
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	@SuppressWarnings("static-access")
	@Then("User verifies login page fields")
	public void user_verifies_login_page_fields() throws InterruptedException {
		Thread.sleep(2000);
		LoginPage_Locators.username().isDisplayed();
		LoginPage_Locators.password().isDisplayed();
		LoginPage_Locators.loginBtn().isDisplayed();
	}

	@SuppressWarnings("static-access")
	@And("User verifies Hireprous logo")
	public void user_verifies_hireprous_logo() throws InterruptedException {
		LoginPage_Locators.logo().isEnabled();
		Thread.sleep(2000);
	}

	//////////////////////Login to application with Invalid user credentials////////////////////////////////////
	@SuppressWarnings("static-access")
	@Given("User is on Hireprous page")
	public void user_is_on_hireprous_page() throws InterruptedException {
		LoginPage_Locators.logo().isDisplayed();
		Thread.sleep(2000);
	}
	@SuppressWarnings("static-access")
	@When("User enters invalid username and password")
	public void user_enters_invalid_username_and_password() throws IOException, InterruptedException {
		LoginPage_Locators.username().isDisplayed();
		LoginPage_Locators.password().isDisplayed();
		LoginPage_Locators.loginBtn().isDisplayed();
		LoginPage_Locators.username().sendKeys(TestBase.excelTestData().getCellData("Login", "InvalidUsername" , 2));
		LoginPage_Locators.password().sendKeys(TestBase.excelTestData().getCellData("Login", "Password" , 2));
		Thread.sleep(3000);
	}


	@SuppressWarnings("deprecation")
	@Then("User verifes the error message for invalid credentials")
	public void user_verifes_the_error_message_for_invalid_credentials() {
		String expectedErrormessage = "User Name - is invalid.";
		String actualErrormessage =LoginPage_Locators.loginErrormessage().getText();
		Assert.assertEquals(expectedErrormessage, actualErrormessage);
	}
	//////////////////////Login to application with Valid user credentials////////////////////////////////////

	@Given("User is on Hireprous login page")
	public void user_is_on_hireprous_login_page() throws InterruptedException {

		Boolean Display = driver.findElement(By.xpath(Objectlocators.logo)).isDisplayed();
		System.out.println("Element displayed is :"+Display);
		Thread.sleep(2000);
	}

	@When("User enters username and password")
	public void user_enters_username_and_password() throws InterruptedException, IOException {
		LoginPage_Locators.username().clear();
		LoginPage_Locators.username().sendKeys(TestBase.excelTestData().getCellData("Login", "Username" , 2));
		LoginPage_Locators.password().clear();
		LoginPage_Locators.password().sendKeys(TestBase.excelTestData().getCellData("Login", "Password" , 2));
		Thread.sleep(1000);
	}

	@And("User clicks on Log IN button")
	public void user_clicks_on_log_in_button() throws InterruptedException {
		Thread.sleep(2000);
		LoginPage_Locators.loginBtn().click();
	}
	@Then("User login must be successful")
	public void user_login_must_be_successful() throws InterruptedException {
		Thread.sleep(3000);
		LoginPage_Locators.logo().isDisplayed();
		driver.quit();

	}
}


