package UserPortal;
/*package a_UserPortal;
//package UserPortal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Register {
    
	WebDriver wd=null;
	
	@Before
	public void init() {
		System.out.println("This is before1");
		System.setProperty("webdriver.chrome.driver","C:\\Users\\tanuj\\OneDrive\\Desktop\\tanu\\full stack\\Capstone Projects\\chromedriver_win32\\chromedriver.exe");
		wd=new ChromeDriver();
		
		wd.get("http://localhost:8080");
	}

	@Test
	public void test() throws InterruptedException {
        
		System.out.println("This is test1");
		String actual=wd.findElement(By.xpath("/html/body/h1")).getText();
		String expected="Welcome to Medicare,e-healthcare web application";
		
		assertEquals(expected,actual);
		
		wd.findElement(By.linkText("Register")).click();
		Thread.sleep(2000);
        
		String actual1=wd.findElement(By.xpath("/html/body/h1")).getText();
		String expected1="Register";
		
		assertEquals(expected1,actual1);
		
		wd.findElement(By.name("name")).sendKeys("Ram Jet");
		wd.findElement(By.name("pwd")).sendKeys("45673");
		wd.findElement(By.name("phone")).sendKeys("66666");
		wd.findElement(By.name("email")).sendKeys("ram@hotmail.com");
		wd.findElement(By.name("address")).sendKeys("Janak Puri New Delhi");
		WebElement userType=wd.findElement(By.name("uType"));
		Select u1=new Select(userType);
		
		u1.selectByVisibleText("Customer");
		Thread.sleep(6000);
		
		wd.findElement(By.tagName("button")).click();
		
		String actual2=wd.findElement(By.xpath("/html/body/h2")).getText();
		String expected2="User Ram Jet registered successfully";
		
		assertEquals(expected2,actual2);
		Thread.sleep(6000);
		
	/*	wd.findElement(By.linkText("Sign In")).click();
		String actual3=wd.findElement(By.xpath("/html/body/h1")).getText();
		String expected3="Sign In";
		
		assertEquals(expected3,actual3);
		
		wd.findElement(By.name("uname")).sendKeys("Ram Jet");
		wd.findElement(By.name("pwd")).sendKeys("45673");
		wd.findElement(By.tagName("button")).click();
		Thread.sleep(9000);
		
		String actual4=wd.findElement(By.xpath("/html/body/h1")).getText();
		String expected4="Welcome Ram Jet to Customer Dashboard";
		
		assertEquals(expected4,actual4); */
//	} 
	
	
	/*@Before
	public void signin() throws InterruptedException {
	
		System.out.println("This is Befor2");
		wd.findElement(By.linkText("Sign In")).click();
		String actual3=wd.findElement(By.xpath("/html/body/h1")).getText();
		String expected3="Sign In";
		
		assertEquals(expected3,actual3);
		
		wd.findElement(By.name("uname")).sendKeys("Ram Jet");
		wd.findElement(By.name("pwd")).sendKeys("45673");
		wd.findElement(By.tagName("button")).click();
		Thread.sleep(9000);
		
		String actual4=wd.findElement(By.xpath("/html/body/h1")).getText();
		String expected4="Welcome Ram Jet to Customer Dashboard";
		
		assertEquals(expected4,actual4);
	}
	
	
	@Test
	public void test2() {
     
		System.out.println("This is test2");
	            	
	}*/

//}
