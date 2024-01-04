package Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import common.XlsReader;

public class TestBase {

	public static final String driverPath = "./Drivers/chromedriver.exe";
	public static final String excelTestData = "./TestData/InputData.xlsx";
	public static XlsReader data;
	public static Properties prop;
	public static WebDriver driver;
	public static XlsReader excelTestData() throws IOException {
		
        prop = new Properties();
		FileInputStream file = new FileInputStream(
		System.getProperty("user.dir") + "/src/test/java/Configuration/environment.properties");
		prop.load(file);
		String envVar = prop.getProperty("ENV");
        String excelTestData = "./TestData/" + envVar + ".xlsx";
        data = new XlsReader(System.getProperty("user.dir") + excelTestData);
        return data;
	}

public static void env() {
	System.setProperty("webdriver.chrome.driver", TestBase.driverPath);
	driver = new ChromeDriver();
}
}



