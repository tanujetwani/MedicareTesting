package UserPortal;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;
import utils.DBUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class BuyingMedicines {

WebDriver wd=null;
	
	@Before
	public void init() {
		System.out.println("This is Before");
		System.setProperty("webdriver.chrome.driver","C:\\Users\\tanuj\\OneDrive\\Desktop\\tanu\\full stack\\Capstone Projects\\chromedriver_win32\\chromedriver.exe");
		wd=new ChromeDriver();
		
		wd.get("http://localhost:8080");
		wd.manage().window().maximize();
	}
	
	//testA() for registering user
	@Test
	public void testA() throws InterruptedException {
        
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
		
	} 
	
	//TestB() for buying and ordering medicines
	@Test
	public void testB() throws InterruptedException, ClassNotFoundException, SQLException {
        System.out.println("This is Test1");    
		String actual=wd.findElement(By.xpath("/html/body/h1")).getText();
		String expected="Welcome to Medicare,e-healthcare web application";
		
		assertEquals(expected,actual);
		
		
		//Sign in to the web-application
		
		wd.findElement(By.linkText("Sign In")).click();
		String actual3=wd.findElement(By.xpath("/html/body/h1")).getText();
		String expected3="Sign In";
		
		assertEquals(expected3,actual3);
		
		wd.findElement(By.name("uname")).sendKeys("Ram Jet");
		wd.findElement(By.name("pwd")).sendKeys("45673");
		Thread.sleep(5000);
		wd.findElement(By.tagName("button")).click();
		
		
		//In the Customer Dashboard
		
		String actual4=wd.findElement(By.xpath("/html/body/h1")).getText();
		String expected4="Welcome Ram Jet to Customer Dashboard";
		
		
		assertEquals(expected4,actual4);
		//Searching for 'fever' medicines
		//String searchterm="fever";
		wd.findElement(By.name("searchkey")).sendKeys("fever");
		Thread.sleep(5000);
		wd.findElement(By.xpath("/html/body/div[1]/form/button")).click();
		
		List<WebElement> results=wd.findElements(By.className("table-danger"));
		
		//Getting fever medicines from database
		Connection con=DBUtil.getConnection();
    	String sql="select * from product where CONCAT(name,'',category,'',description,'',seller,'',price,'') like ?";
    	PreparedStatement ps=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
    	ps.setString(1, "%fever%");
    	ResultSet rs=ps.executeQuery();
    	//rs.last();
    	//int  count=rs.getRow();
    	int count=0;
    	while(rs.next()) {count++;}
    	
    	boolean pass=true;
    	
    	if(count==results.size()) 
    	           { System.out.println("The number of records in database and frontend are same.");
    	           }
    	else {
    		 System.out.println("The number of records in database do not match with no. of records in frontend. ");
    	     pass=false;
    	}
    	
    	
		//if(results.size()>0) {
		for(int i=0; i<results.size();i++) {
			System.out.println("i :"+ i);
			//Assert.assertTrue(results.get(i).getText().contains("fever"));
			 System.out.println(results.get(i).getText());
			 
			// String actual5=null;
			 //String expected5=null;
			 if(results.get(i).getText().contains("fever")) {
				// actual5="fever";
				 //expected5="fever";
				 //assertEquals(expected5,actual5);
			 }
						  
				 //actual5="cold";
			  //expected5="cold";
			 
			 //assertEquals(expected5,actual5);
			 
			
			 
			 else {
			      System.out.println("Incorrect search results");	 
                  pass=false;			 
			 }
			
			//Assert.assertTrue(results.get(i).getText().contains("cold"));
			
		}  //End of for
			 
		//}//End of if
	/*	else {		
		        System.out.println("No search results found");
                 	}
			*/ 
	
	     assertEquals(true,pass);
	    /*if(pass==true) {
	    	
	    	System.out.println("Test Case Passed");
	    }
	    else {
	    	System.out.println("Test Case Fail");
	    }*/
	     
	     //Testing addToCart
	     //WebElement hidden=wd.findElement(By.name("button1"));	
	     Thread.sleep(4000);
	     
	     List<String> prodNameinSearchResults=new ArrayList<String>();
	     
	     for(int i=2;i<results.size()+2;i++) {
	    	 
	    	 //String prodName1[]=results.get(i).getText().split(" ");
	    	 String prodName1=wd.findElement(By.xpath("/html/body/table/tbody/tr["+i+"]/th")).getText();
	    	 prodNameinSearchResults.add(prodName1);
	     }
	     
	     for(int i=0; i<results.size(); i++) {
	    	 
	    	 
	    	 results.get(i).findElement(By.name("button1")).click();
	    	 results=wd.findElements(By.className("table-danger"));
	    	 Thread.sleep(3000);
	    	 
	    	 String actual5=wd.findElement(By.xpath("/html/body/h4[2]")).getText();
	         String expected5= "Added "+ prodNameinSearchResults.get(i) + " to cart";
	         
	         assertEquals(expected5,actual5);
	     }
	     /*WebElement addTCart=wd.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[5]/form/button"));
	     addTCart.click();
	    
	     Thread.sleep(9000);
	     String actual5=wd.findElement(By.xpath("/html/body/h4[2]")).getText();
			
		String expectedName=wd.findElement(By.xpath("/html/body/table/tbody/tr[2]/th[1]")).getText();
		
		String expected5="Added "+expectedName+ " to cart";
		assertEquals(expected5,actual5);   
		
		System.out.println("Actual5 is: "+ actual5);
		wd.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[5]/form/button")).click();
		Thread.sleep(9000);
		String prodName2=wd.findElement(By.xpath("/html/body/table/tbody/tr[3]/th[1]")).getText();
        
		String expected7="Added "+ prodName2 + " to cart";
		String actual7=wd.findElement(By.xpath("/html/body/h4[2]")).getText();
		
		System.out.println("Actual7: "+ actual7);
		System.out.println("ProdName2:"+ prodName2); */
	     
	     
	     
		//Click on View Cart button
		wd.findElement(By.xpath("/html/body/div[2]/form/button")).click();
		Thread.sleep(5000);
	    
	     
		String actual6=wd.findElement(By.xpath("/html/body/h2")).getText();
		
		String expected6="Cart Details of User Ram Jet";
		
		assertEquals(expected6,actual6);
		
		//Comparing the products in "View Cart" to products added from search results
		
		List<WebElement> prodinCart1=wd.findElements(By.className("table-success")); 
		
		List<String> prodNamesinCart1= new ArrayList<String>();
		
		for(int i=2;i<prodinCart1.size()+2;i++) {
			
			      //String prod2[]=prodinCart1.get(i).getText().split(" ");
			String prod2=wd.findElement(By.xpath("/html/body/table/tbody/tr["+i+"]/td[1]")).getText();      
			prodNamesinCart1.add(prod2);
		}
           
		for(int i=0; i<prodNameinSearchResults.size(); i++) {
			
		         assertEquals(prodNameinSearchResults.get(i), prodNamesinCart1.get(i));	
			
		}
		
		
		/*String actualName=wd.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[1]")).getText();
		System.out.println("Actual Name 1 :"+ actualName);
		
		String actualName2=wd.findElement(By.xpath("html/body/table/tbody/tr[3]/td[1]")).getText();
		System.out.println("Actual Name 2: "+ actualName2); */
		// now1 assertEquals(expectedName,actualName);
		
	    //now1 assertEquals(prodName2,actualName2);	
          	     
	     //wd.findElement(By.xpath("/html/body/div[2]/form/button")).click();

	     
	         //Testing Remove from Cart
            	
	         String prodName2 =wd.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[1]")).getText();
	         
	         wd.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[7]/form/button")).click();
	         Thread.sleep(7000);
	         
	         String remCartActual=wd.findElement(By.xpath("/html/body/h3")).getText();
	         
	        //String remCartExpected="Removed "+ wd.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[1]")).getText() + " from cart";
	         
	          String remCartExpected="Removed "+ prodName2 + " from cart";
	         
	         assertEquals(remCartExpected,remCartActual);
	         
	         //List of Products in Cart Final
	         
	         List<WebElement> prodinCart= wd.findElements(By.className("table-success"));
	        
	          int noOfProdinCart=prodinCart.size();
	          
	         List<String> prodNamesinCart=new ArrayList<String>();
	         
	         for(int i=2;i<prodinCart.size()+2;i++) {
	        	 
	        	 //String prod[]=prodinCart.get(i).getText().split(" ");
	        	 String prod=wd.findElement(By.xpath("/html/body/table/tbody/tr["+i+"]/td[1]")).getText();
	        	 prodNamesinCart.add(prod);
	         }

	         
	         //Testing button "buy Products"
	         
	         wd.findElement(By.xpath("/html/body/div[1]/form/button")).click();
	         
	         Thread.sleep(5000);
	         String prodBoughtexpected="List of products to be bought";
	         
	         String prodBoughtActual= wd.findElement(By.xpath("/html/body/h2[1]")).getText();
	         
	         assertEquals(prodBoughtexpected,prodBoughtActual);
	         
	         /*String prodBoughtName1Actual=wd.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[1]")).getText();         
	         
	         assertEquals(expectedName,prodBoughtName1Actual); */
	         
	         List<WebElement> prodinBuyProducts=wd.findElements(By.className("table-warning"));
	         
	         //Checking if all the prod in cart are equal in number with prod in buy products 
	        assertEquals(noOfProdinCart,prodinBuyProducts.size());
	    	   
               //List of products in BuyProducts page
	        
	        List<String> prodNameinBuyProd=new ArrayList<String>();
	        
	        for(int i=2;i<noOfProdinCart+2;i++) {
	        	
	        	String prod=wd.findElement(By.xpath("/html/body/table/tbody/tr["+i+"]/td[1]")).getText();
	        	prodNameinBuyProd.add(prod);
	        }
	      	         
	         
	         for(int i=0; i<noOfProdinCart;i++) {
	               
	        	// String prodBoughtActual= wd.findElement(By.xpath("/html/body/h2[1]")).getText();
		         
		         //String prodBoughtName1Actual=wd.findElement(By.xpath("/html/body/table/tbody/tr[2"+i+"]/td[1]")).getText();         
		         
	        //	 String prods[]= prodinBuyProducts.get(i).getText().split(" ");
		  //       String tokens[]=prodinCart.get(i).getText().split(" ");
		         
		         
		         
		         //System.out.println("Tokens[2]: "+ tokens[2]);
		         assertEquals(prodNamesinCart.get(i),prodNameinBuyProd.get(i));
	        	 
	         }
	         
	      
	           //Testing Credit card details page
	         wd.findElement(By.xpath("//button[text()='Pay the Amount']")).click();
	         Thread.sleep(2000);
	         
	         String expectedpaymentGateway="Payment Gateway";
	         String actualpaymentGateway=wd.findElement(By.tagName("h1")).getText();
	         
	         assertEquals(expectedpaymentGateway,actualpaymentGateway);
	         
	         //Enter the card details
	         wd.findElement(By.name("first")).sendKeys("7777");
	         wd.findElement(By.name("second")).sendKeys("8888");
	         wd.findElement(By.name("third")).sendKeys("3333");
	         wd.findElement(By.name("fourth")).sendKeys("4444");
	         
	         WebElement validMonth=wd.findElement(By.name("validmonth"));
	         Select m1=new Select(validMonth);
	         
	         m1.selectByVisibleText("5");
	         
	         WebElement validYear=wd.findElement(By.name("validyear"));
	         Select y1=new Select(validYear);
	         y1.selectByVisibleText("26");
	         
	         
	         wd.findElement(By.name("cvv")).sendKeys("765");
	         
	         WebElement cardType=wd.findElement(By.name("cardType"));
	         Select c1=new Select(cardType);
	         c1.selectByVisibleText("Citigroup");
	         
	         Thread.sleep(4000);
	         
	         wd.findElement(By.xpath("//button[text()='Submit']")).click();
	         Thread.sleep(9000);
	             
	         //Testing Order Summary page
	         
	        // wd.findElement(By.xpath("//button[text()='Pay the Amount']")).click();
	         //Thread.sleep(9000);
	         
	         String ExpectedOrderpage="Thanks Ram Jet for Shopping with us.Your Order is confirmed.";
	         
	         String ActualOrderpage=wd.findElement(By.xpath("/html/body/h3[1]")).getText();
	         
	         assertEquals(ExpectedOrderpage,ActualOrderpage);
	         
	        
	         List<WebElement> prodinOrderpage=wd.findElements(By.className("table-warning"));
	         
	         //Checking the no. of prod in cart are same as in Order page
	         assertEquals(noOfProdinCart, prodinOrderpage.size());
	          
	         boolean pass2=true;
	         for(int i=2; i< noOfProdinCart+2;i++) {
	        	 
	        // String ActualProdBought1=wd.findElement(By.xpath("/html/body/table[2]/tbody/tr[2+i]/td[1]")).getText();
	         
	        //String prods2[]=prodinOrderpage.get(i).getText().split(" ");	 
	         //String tokens2[]=prodinCart.get(i).getText().split(" ");
	         String prods2=wd.findElement(By.xpath("/html/body/table[2]/tbody/tr["+i+"]/td[1]")).getText();
	         //System.out.println("Tokens2[1] :" + tokens2[2]);
	         
	        if(prodNamesinCart.contains(prods2)) {
	        
	        }
	        else {
	             pass2=false;	
	        }
	         //assertEquals(prodNamesinCart.get(i),prods2[1]);
	         }  //End of for
	         
	
	      assertEquals(true,pass2);
	
	} //End of TestB()       
	
	
	
	
	//@After
	/*public void close() {
		System.out.println("this is close");
		wd.close();
	} */

	

}