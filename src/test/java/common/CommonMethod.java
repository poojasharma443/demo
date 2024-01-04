package common;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Configuration.TestBase;
import Locators.Objectlocators;

public class CommonMethod {

	public static void login (String username , String password , WebDriver driver) throws IOException, InterruptedException {

		driver.get(TestBase.excelTestData().getCellData("Login", "URL" , 2));
		driver.manage().window().maximize();
		System.out.println(username);
		driver.findElement(By.name(Objectlocators.UserName)).sendKeys(username);
		driver.findElement(By.name(Objectlocators.Password)).sendKeys(password);
		driver.findElement(By.xpath(Objectlocators.loginBtn)).click();
		Thread.sleep(3000);

	}
	public static String getExcelData(String filepath, String SheetName,String Row, String Column) throws IOException {
		int Rnumber = Integer.parseInt(Row);
		int Cnumber = Integer.parseInt(Column);
		XSSFWorkbook wb = new XSSFWorkbook(filepath);
		XSSFSheet sheet = wb.getSheet(SheetName);
		Row row = sheet.getRow(Rnumber);
		Cell cell = row.getCell(Cnumber);
		String value = cell.getStringCellValue();
		return value;
	}

	public static String getRowCount(String fileLocation,String SheetName) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook(fileLocation);
		XSSFSheet sheet= wb.getSheet(SheetName);
		int row =sheet.getPhysicalNumberOfRows();
		String rowCount =Integer.toString(row);
		System.out.println("////////////////////////////////////" + rowCount + "/////////////////////////////");
		return rowCount;	

	}

}
