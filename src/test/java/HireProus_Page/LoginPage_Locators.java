package HireProus_Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Configuration.TestBase;
import TestStepdef.Stepdef;

public class LoginPage_Locators extends TestBase {
public static WebElement logo() {

		return driver.findElement(By.xpath("//*[@id='hirepro'] | //*[@src='assets/img/Hireprous.png']"));

	}

	public static WebElement username() {

		return driver.findElement(By.name("email"));

	}
	public static WebElement password() {

		return driver.findElement(By.name("password"));

	}
	public static WebElement loginBtn() {

		return driver.findElement(By.xpath("//*[@class='btn btn-primary form-control']"));

	}
	public static WebElement loginErrormessage() {

		return driver.findElement(By.xpath("//*[@class='alertify-notifier ajs-top ajs-right']"));

	}
	public static WebElement Dashboard() {

		return driver.findElement(By.xpath("//*[@id='dashboard'] | //*[@class='nav-link active']"));

	}

}
