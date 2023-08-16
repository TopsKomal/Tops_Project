package com.DataDrivenFW;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.google.common.io.Files;
import junit.framework.Assert;

public class Swag_Labs {
	public String[][] readExcel() throws InvalidFormatException, IOException {

		String[][] data = null;

		// 1.to set file path
		String file_path = "E:\\komal_seleniam\\Tops Project Swag Labs.xlsx";

		// 2.to make file
		File file = new File(file_path);

		// 3.to open workbook
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		// 4.to open a particular sheet
		Sheet sheet = workbook.getSheet("Sheet1");

		// 5.to open a particular row
		// no of row
		int n_row = sheet.getPhysicalNumberOfRows();
		System.out.println("no of rows :" + n_row);

		data = new String[n_row][];

		for (int i = 0; i < data.length; i++) {
			// 6. to select a row
			Row row = sheet.getRow(i);
			// 7.to get a particular col
			int n_col = row.getPhysicalNumberOfCells();
			System.out.println("no of cells in each row are: " + n_col);
			data[i] = new String[n_col];
			for (int j = 0; j < data[i].length; j++) {
				// 8.to select a col
				Cell cell = row.getCell(j);

				// 9.to set cell to string
				cell.setCellType(CellType.STRING);

				data[i][j] = cell.getStringCellValue();
			}
		}
		return data;
	}

	WebDriver driver = null;

	@Test
	public void verifySwagLabs() throws InterruptedException, InvalidFormatException, IOException {
		String[][] data = readExcel();
		System.setProperty("webdriver.chrome.driver", "E:\\komal_seleniam\\chromedriver_win32\\chromedriver.exe");

		for (int i = 0; i < data.length; i++) {

			driver = new ChromeDriver();
			driver.manage().window().maximize();

			driver.get("https://www.saucedemo.com");
			driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));

			driver.findElement(By.id("user-name")).sendKeys(data[i][0]);

			driver.findElement(By.id("password")).sendKeys(data[i][1]);

			driver.findElement(By.id("login-button")).click();

			if (driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {

				System.out.println("your test is passed");

				// side menu
				driver.findElement(By.xpath("//div[@class='bm-burger-button']//button")).click();

				driver.findElement(By.id("inventory_sidebar_link")).click();

				driver.findElement(By.id("about_sidebar_link")).click();

				driver.navigate().back();

				driver.findElement(By.xpath("//div[@class='bm-burger-button']//button")).click();

				driver.findElement(By.id("reset_sidebar_link")).click();

				driver.navigate().refresh();

				// side drop down name
				WebElement e1 = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
				Select s1 = new Select(e1);
				s1.selectByIndex(0);
				Thread.sleep(2000);

				WebElement e2 = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
				Select s2 = new Select(e2);
				s2.selectByIndex(1);
				Thread.sleep(2000);

				WebElement e3 = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
				Select s3 = new Select(e3);
				s3.selectByIndex(2);
				Thread.sleep(2000);

				WebElement e4 = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
				Select s4 = new Select(e4);
				s4.selectByIndex(3);
				Thread.sleep(2000);

				// Add to cart button
				driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();

				// Add to cart icon
				driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

				// Add to cart page
				driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();

				// Checkout information page
				driver.findElement(By.id("first-name")).sendKeys("pooja");
				Thread.sleep(2000);

				driver.findElement(By.id("last-name")).sendKeys("patil");
				Thread.sleep(2000);

				driver.findElement(By.id("postal-code")).sendKeys("123456");
				Thread.sleep(2000);

				driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();

				// Checkout overview page
				if (driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html")) {
					System.out.println("your test is passed");

					driver.findElement(By.xpath("//*[@id=\"finish\"]")).click();

					// Checkout Complete! page
					Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");

					WebElement msgElement = driver
							.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/div"));
					String msg = msgElement.getText();
					System.out.println(msg);
					Assert.assertEquals(msg,
							"Your order has been dispatched, and will arrive just as fast as the pony can get there!");

					driver.findElement(By.xpath("//*[@id=\"back-to-products\"]")).click();

					// Home page
					driver.findElement(By.xpath("//div[@class='bm-burger-button']//button")).click();
					Thread.sleep(2000);

					driver.findElement(By.id("logout_sidebar_link")).click();
					Thread.sleep(2000);

				} else {
					System.out.println("fail");
					File file = ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
					Files.copy(file, new File("E:\\SS\\defect locked out user.pntg"));
				}

			} else {
				System.out.println("fail");
				File file = ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
				Files.copy(file, new File("E:\\SS\\defect problem user.pntg"));
			}
			driver.close();
		}
	}
}