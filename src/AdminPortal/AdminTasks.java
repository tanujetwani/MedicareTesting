package AdminPortal;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import utils.DBUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AdminTasks {

	WebDriver wd=null;
	
	@Before
	public void init() {
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\tanuj\\OneDrive\\Desktop\\tanu\\full stack\\Capstone Projects\\chromedriver_win32\\chromedriver.exe");
	    wd=new ChromeDriver();
	}
	
	
	//TestA() for registering the admin
	@Test
	public void testA() throws InterruptedException {
	
	          wd.get("http://localhost:8080");
	          wd.manage().window().maximize();
              
	          String actualHomemsg=wd.findElement(By.xpath("/html/body/h1")).getText();
	          String expectedHomemsg="Welcome to Medicare,e-healthcare web application";
	          
	          assertEquals(expectedHomemsg,actualHomemsg);
	          
	          wd.findElement(By.linkText("Register")).click();
	          
	          Thread.sleep(4000);
	          
	        String actualRegister=wd.findElement(By.xpath("/html/body/h1")).getText();
	  		String expectedRegister="Register";
              
	  		assertEquals(expectedRegister,actualRegister);
	  		
	  		wd.findElement(By.name("name")).sendKeys("admin2");
			wd.findElement(By.name("pwd")).sendKeys("asdfg");
			WebElement userType=wd.findElement(By.name("uType"));
			Select u1=new Select(userType);
			
			u1.selectByVisibleText("Admin");
			Thread.sleep(6000);
			
			wd.findElement(By.xpath("//button[text()='Register']")).click();
			
			String actualRegister2=wd.findElement(By.xpath("/html/body/h2")).getText();
			String expectedRegister2="User admin2 registered successfully";
			
			assertEquals(expectedRegister2,actualRegister2);
			
			Thread.sleep(6000);
			
			
	}//End of testA()
	
	
	
	
	
	//TestB() for adding a medicine
	@Test
	public void testB() throws InterruptedException, ClassNotFoundException, SQLException {
		
		     wd.get("http://localhost:8080");
		     wd.manage().window().maximize();
		     
		     String actualHomemsg=wd.findElement(By.xpath("/html/body/h1")).getText();
	          String expectedHomemsg="Welcome to Medicare,e-healthcare web application";
	          
	          assertEquals(expectedHomemsg,actualHomemsg);
	          
              wd.findElement(By.linkText("Sign In")).click();
	          
	          Thread.sleep(4000);
	          
	        String actualSignIn=wd.findElement(By.xpath("/html/body/h1")).getText();
	  		String expectedSignIn="Sign In";
	  		
	  		assertEquals(expectedSignIn,actualSignIn);
	  		
	  		wd.findElement(By.name("uname")).sendKeys("admin2");
			wd.findElement(By.name("pwd")).sendKeys("asdfg");
			Thread.sleep(5000);
			wd.findElement(By.tagName("button")).click();
			
			//Check if in the admin Dashboard
			Thread.sleep(2000);
			String actualAdminDboard=wd.findElement(By.xpath("/html/body/h1")).getText();
			String expectedAdminDboard="Welcome admin2 to Admin Portal";
			
			assertEquals(expectedAdminDboard,actualAdminDboard);
			
			
			//Click on add medicine link
			wd.findElement(By.partialLinkText("Add")).click();
			
			String actualAdd=wd.findElement(By.xpath("/html/body/h1")).getText();
			String expectedAdd="Add a medicine";
			
			assertEquals(expectedAdd,actualAdd);
			
			wd.findElement(By.name("name")).sendKeys("Bandy Plus");
			WebElement category=wd.findElement(By.name("category"));
			Select c1=new Select(category);
			
			c1.selectByValue("allergy");
			
			wd.findElement(By.name("description")).sendKeys("abc");
			wd.findElement(By.name("seller")).sendKeys("mfine");
			wd.findElement(By.name("price")).sendKeys("80.4");
			WebElement is_avail=wd.findElement(By.name("isAvailable"));
			
			Select a1=new Select(is_avail);
			
			a1.selectByVisibleText("Yes");
			
			Thread.sleep(5000);
			wd.findElement(By.xpath("//button[text()='Add medicine']")).click();
			
			Thread.sleep(4000);
			String actualAddMedicine=wd.findElement(By.tagName("h2")).getText();
			String expectedAddMedicine="Medicine Bandy Plus Added successfully";
			
			assertEquals(expectedAddMedicine,actualAddMedicine);
			
			//Checking if medicine added in database
			Connection con=DBUtil.getConnection();
	    	String sql="select * from product where name=?";
	    	PreparedStatement ps=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
	    	ps.setString(1, "Bandy Plus");
	    	ResultSet rs=ps.executeQuery();
	    	
	    	boolean pass=false;
	    	while(rs.next()){pass=true;} 
	    		
	    	    	
	    	
	    	
	    	assertEquals(true,pass);
			
	}
	
	
	//testC() for edit the medicine details
	     @Test
	     public void testC() throws InterruptedException, ClassNotFoundException, SQLException {
	    	 
	    	 
                wd.get("http://localhost:8080");
                wd.manage().window().maximize();
		     
		     String actualHomemsg=wd.findElement(By.xpath("/html/body/h1")).getText();
	          String expectedHomemsg="Welcome to Medicare,e-healthcare web application";
	          
	          assertEquals(expectedHomemsg,actualHomemsg);
	          
              wd.findElement(By.linkText("Sign In")).click();
	          
	          Thread.sleep(4000);
	          
	        String actualSignIn=wd.findElement(By.xpath("/html/body/h1")).getText();
	  		String expectedSignIn="Sign In";
	  		
	  		assertEquals(expectedSignIn,actualSignIn);
	  		
	  		wd.findElement(By.name("uname")).sendKeys("admin2");
			wd.findElement(By.name("pwd")).sendKeys("asdfg");
			Thread.sleep(5000);
			wd.findElement(By.tagName("button")).click();
			
			//Check if in the admin Dashboard
			Thread.sleep(2000);
			String actualAdminDboard=wd.findElement(By.xpath("/html/body/h1")).getText();
			String expectedAdminDboard="Welcome admin2 to Admin Portal";
			
			assertEquals(expectedAdminDboard,actualAdminDboard);
			
            
			//Click on Edit Medicine Details
			
			wd.findElement(By.partialLinkText("Edit")).click();
			
			String actualEdit=wd.findElement(By.xpath("/html/body/h1")).getText();
			
			String expectedEdit="Edit the Details";
			
			assertEquals(expectedEdit,actualEdit);
			

			 wd.findElement(By.name("pid")).sendKeys("1");
			 wd.findElement(By.xpath("//button[text()='Search Medicine']")).click();
			 
			 String actualId=wd.findElement(By.xpath("/html/body/form[2]/b[1]")).getText();
			 
			String expectedId="1";
			
			assertEquals(expectedId,actualId);
			
			//Edit the details of medicine
			wd.findElement(By.name("name")).sendKeys("Kuka");
			WebElement category=wd.findElement(By.name("category"));
			Select c1=new Select(category);
			c1.selectByValue("cough");
			wd.findElement(By.name("description")).sendKeys("abc");
			wd.findElement(By.name("seller")).sendKeys("mfine");
			wd.findElement(By.name("price")).sendKeys("75.6");
			
			Thread.sleep(5000);
			wd.findElement(By.xpath("//button[text()='Edit the details']")).click();
			
			Thread.sleep(6000);
			
			String actualEditMedicine=wd.findElement(By.xpath("/html/body/h2")).getText();
			
			String expectedEditMedicine="Details of medicine with Id "+ expectedId + " edited .";
			
			assertEquals(expectedEditMedicine,actualEditMedicine);
			
			
			//Check if details are edited in database
			
			Connection con=DBUtil.getConnection();
	    	String sql="select * from product where pid=?";
	    	PreparedStatement ps=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
	    	ps.setString(1, expectedId);
	    	ResultSet rs=ps.executeQuery();
	    	
	    	
	    	while(rs.next()) {	
	    	
	    		String pid=Integer.toString(rs.getInt("pid"));
	    		String pname=rs.getString("name");
	    		String pcategory=rs.getString("category");
	    		String pdescription=rs.getString("description");
	    		String pseller=rs.getString("seller");
	    		String price=rs.getString("price");
	    	
	    	assertEquals(expectedId,pid);
	    	assertEquals("Kuka",pname);
	    	assertEquals("cough",pcategory);
	    	assertEquals("abc",pdescription);
	    	assertEquals("mfine",pseller);
	    	assertEquals("75.6",price);
	    	
	    }

     } //End of testC()
	     
        //testD() for enable or disable a medicine	     
	     
	     @Test
	     public void testD() throws InterruptedException, ClassNotFoundException, SQLException {
	    	 
             wd.get("http://localhost:8080");
             wd.manage().window().maximize();
             
		     String actualHomemsg=wd.findElement(By.xpath("/html/body/h1")).getText();
	          String expectedHomemsg="Welcome to Medicare,e-healthcare web application";
	          
	          assertEquals(expectedHomemsg,actualHomemsg);
	          
              wd.findElement(By.linkText("Sign In")).click();
	          
	          Thread.sleep(4000);
	          
	        String actualSignIn=wd.findElement(By.xpath("/html/body/h1")).getText();
	  		String expectedSignIn="Sign In";
	  		
	  		assertEquals(expectedSignIn,actualSignIn);
	  		
	  		wd.findElement(By.name("uname")).sendKeys("admin2");
			wd.findElement(By.name("pwd")).sendKeys("asdfg");
			Thread.sleep(5000);
			wd.findElement(By.tagName("button")).click();
			
			//Check if in the admin Dashboard
			Thread.sleep(2000);
			String actualAdminDboard=wd.findElement(By.xpath("/html/body/h1")).getText();
			String expectedAdminDboard="Welcome admin2 to Admin Portal";
			
			assertEquals(expectedAdminDboard,actualAdminDboard);
             
			
			//Click on the link Enable or disable a medicine
			wd.findElement(By.partialLinkText("Enable")).click();
			String actualEnable=wd.findElement(By.xpath("/html/body/h2[1]")).getText();
			String expectedEnable="Enable or Disable a Medicine";
			
			assertEquals(expectedEnable,actualEnable);
			
			wd.findElement(By.name("pid")).sendKeys("1");
			wd.findElement(By.xpath("//button[text()='Search Medicine']")).click();
			
			List<WebElement> isAvailable=wd.findElements(By.name("enable"));
			
			//Disable the medicine
			isAvailable.get(1).click();
			
			//Click on Submit
			wd.findElement(By.xpath("//button[text()='Submit']")).click();
			Thread.sleep(5000);
			
			//String enableMedicineActual=wd.findElement(By.xpath("/html/body/h2[2]")).getText();
			String enableMedicineActual=wd.findElement(By.tagName("h3")).getText();
			String enableMedicineExpected="Disabled the medicine with id 1";
			
			assertEquals(enableMedicineExpected,enableMedicineActual);
			
			
			//Check in database
			
			Connection con=DBUtil.getConnection();
	    	String sql="select * from product where pid=?";
	    	PreparedStatement ps=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
	    	ps.setString(1, "1");
	    	ResultSet rs=ps.executeQuery();
	    	
	    	boolean is_avail=true;
	    	while(rs.next()) {
	    		
	    		 is_avail=rs.getBoolean("is_available");
	    		
	    		
	    	}
	    	
	    	assertEquals(false,is_avail);
	    	
			
	     } //End of TestD()
	     
}