package com.mvn;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class Add_To_Cart {
	@Test
	public void verifyAddToCart() {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.saucedemo.com/v1/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.findElement(By.id("user-name")).sendKeys("standard_user");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();

		driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).click();

		driver.findElement(By.xpath("//div[@id='header_container']//div[2]//a")).click();

		driver.findElement(By.xpath("//button[@class='btn_secondary cart_button']")).click();

		driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[1]")).click();

		driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).click();

		driver.findElement(By.xpath("//div[@id='header_container']//div[2]//a")).click();

		driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]")).click();

		driver.findElement(By.id("first-name")).sendKeys("pooja");

		driver.findElement(By.id("last-name")).sendKeys("patil");

		driver.findElement(By.id("postal-code")).sendKeys("123456");

		driver.findElement(By.xpath("//div[@class='checkout_buttons']//a")).click();

		driver.findElement(By.xpath("//div[@id='cart_contents_container']//div/div[2]/a[2]")).click();

		driver.findElement(By.id("first-name")).sendKeys("dipti");

		driver.findElement(By.id("last-name")).sendKeys("patil");

		driver.findElement(By.id("postal-code")).sendKeys("1234567");

		driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[2]/input")).click();
		
		driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]/a[1]")).click();

		driver.findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']")).click();

		driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]")).click();
		
		//driver.findElement(By.xpath("//div[@class='cart_footer']//a[2]")).click();
		
		driver.findElement(By.id("first-name")).sendKeys("priya");

		driver.findElement(By.id("last-name")).sendKeys("patil");

		driver.findElement(By.id("postal-code")).sendKeys("1234568");
		
		driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[2]/input")).click();
		
		driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]/a[2]")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-complete.html");

		WebElement msgElement = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2"));
		String msg = msgElement.getText();
		System.out.println(msg);
		Assert.assertEquals(msg, "THANK YOU FOR YOUR ORDER");

		driver.close();
	}
}