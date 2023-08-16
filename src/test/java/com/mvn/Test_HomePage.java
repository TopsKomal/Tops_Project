package com.mvn;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test_HomePage {
	@Test
	public void verifyMenuIcon() throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.saucedemo.com/v1/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.findElement(By.id("user-name")).sendKeys("standard_user");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();

		driver.findElement(By.xpath("//div[@class='bm-burger-button']//button")).click();

		driver.findElement(By.id("inventory_sidebar_link")).click();

		driver.findElement(By.xpath("//div[@class='bm-burger-button']//button")).click();

		driver.findElement(By.id("about_sidebar_link")).click();

		driver.navigate().back();

		driver.findElement(By.id("reset_sidebar_link")).click();

		driver.findElement(By.id("logout_sidebar_link")).click();

		driver.close();

	}

	@Test
	public void verifyAddToCartButton() {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.saucedemo.com/v1/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.findElement(By.id("user-name")).sendKeys("standard_user");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();

		driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).click();

		driver.close();

	}

	@Test
	public void verifyAddToCartIcon() {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.saucedemo.com/v1/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.findElement(By.id("user-name")).sendKeys("standard_user");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();
		
		driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).click();
		
		driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[3]/button")).click();

		driver.findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']")).click();

		driver.close();

	}
	@Test
	public void verifyNameDorpDwon() {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.saucedemo.com/v1/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.findElement(By.id("user-name")).sendKeys("standard_user");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();

		WebElement e1 = driver.findElement(By.xpath("//select[@class='product_sort_container']"));

		Select s1 = new Select(e1);

		s1.selectByIndex(0);

		WebElement e2 = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
		Select s2 = new Select(e2);
		s2.selectByIndex(1);
		
		WebElement e3 = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
		Select s3 = new Select(e3);
		s3.selectByIndex(2);
		
		WebElement e4 = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
		Select s4 = new Select(e4);
		s4.selectByIndex(3);
		
		driver.close();
	}
}
