package MobileDev;


import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.jna.platform.FileUtils;

public class MobileDev {
	  public static DesiredCapabilities capabilities;
	 // public static AppiumDriverLocalService appiumService;
	  
	  
	  
	  public static String appiumServiceUrl;
	  public static AppiumDriverLocalService appiumService;
	  public static int Bulkratecheck=0;
	  public static int Bulkinvcheck=0;
	  public static int Bulkrescheck=0;
	  public static boolean   RNA = false; 
	  public static boolean execute=true;
	  public static int randomNum;
	  public static WebDriver driver;
      public static WebDriverWait wait;
	  public static Robot robot;
	  public static int[] invday={18,19};
	  public static String[] month={"","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	  public static String[] monthdesc= {"","", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	  public static   String ActualStartdate;
	  public static   String ActualEnddate;
	  public static String []goldengroup={"Manali","Lucknow","Delhi",
              "Gurgaon","Chattarpur","Chandigarh","Ahmedabad","Goa",
              "Kufri","Electronic City","Bhiwadi","Jaipur",
              "Haridwar","Neemrana","Golden Tulip Vasundhara Hotel & Suites","Royal Tulip Sea Pearl Beach Resort & Spa","Kolkata",
              };
	  public static int switchcontrol=0;
	  public static String datestr;
	  public static Random rand = new Random();
	  public static String[] goldengroupnot={"Hotel De Paris"};
	  public static int count=0;
	  //@BeforeSuite
	  public static void setup() throws IOException
	  {
		// Created object of DesiredCapabilities class.
           
		   //String Appium_Node_Path="C:/Program Files (x86)/Appium/node.exe";
		   //String Appium_JS_Path="C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js";


          //Process p = Runtime.getRuntime().exec("\"C:/Program Files (x86)/Appium/node.exe\" \"C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js\" --no-reset --local-timezone");

		  //Random rand = new Random();
		  //RandomNum = rand.nextInt((150-1) + 1) + 1;
	       capabilities = new DesiredCapabilities();

		  // Set android deviceName desired capability. Set your device name.
		  //capabilities.setCapability("deviceName", "LowerMobileVersion:5554");
		  capabilities.setCapability("deviceName", "Nexus6:5554");

		  // Set BROWSER_NAME desired capability. It's Android in our case here.
		  capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

		  // Set android VERSION desired capability. Set your mobile device's OS version.
		 //capabilities.setCapability(CapabilityType.VERSION, "4.4.2");
		  capabilities.setCapability(CapabilityType.VERSION, "6.0.1");

		  // Set android platformName desired capability. It's Android in our case here.
		  capabilities.setCapability("platformName", "Android");

		  // Set android appPackage desired capability. It is
		  // com.android.calculator2 for calculator application.
		  // Set your application's appPackage if you are using any other app.
		  capabilities.setCapability("appPackage", "com.android.launcher3");
		  //capabilities.setCapability("appPackage", "com.android.launcher");

		  // Set android appActivity desired capability. It is
		  // com.android.calculator2.Calculator for calculator application.
		  // Set your application's appPackage if you are using any other app.
		 
		  capabilities.setCapability("appActivity", "com.android.launcher3.Launcher");
		  //capabilities.setCapability("appActivity", "com.android.launcher2.Launcher");

		  //capabilities.setCapability("appActivity", "com.android.email.activity.setup.AccountSetupFinal");
		  //Created object of RemoteWebDriver will all set capabilities.
		  //Set appium server address and port number in URL string.
		  //It will launch calculator app in android device.
		  
		  driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		 
		  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		  wait=new WebDriverWait(driver, 50);
		  }
		 

	  
	  
	  @Test(priority=1,dataProvider="data")
	  public static void startAutomation(String username,char[] Password,String searchStatus,String rateStatus,String invStatus,String resStatus,String CustName,String Custcode)throws InterruptedException, AWTException, IOException
	  {
		// Created object of DesiredCapabilities class.
		  
		  RNA=false;
		  execute=true;
		  count++;
		  if(switchcontrol==0)
		  {
			  System.out.println("NEW");
			  System.out.println("HI");
			   String Appium_Node_Path="C:/Program Files (x86)/Appium/node.exe";
			   String Appium_JS_Path="C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js";
	
			   appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().
		                usingAnyFreePort().usingDriverExecutable(new File(Appium_Node_Path)).
		                withAppiumJS(new File(Appium_JS_Path)));
			  
			   appiumServiceUrl = appiumService.getUrl().toString();
	          appiumService.start();
	         
	         // Thread.sleep(10000);
	          
			
			  //Process p = Runtime.getRuntime().exec("\"C:/Program Files (x86)/Appium/node.exe\" \"C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js\" --no-reset --local-timezone");
	       capabilities = new DesiredCapabilities();
	       capabilities.setCapability("appium-version", "1.4.13.1");
	       capabilities.setCapability("platformName", "Android");
	       capabilities.setCapability("platformVersion", "6.0.1");
		   capabilities.setCapability("deviceName", "Android");
		  //capabilities.setCapability("deviceName", "Nexus6:5554");
		 
		  //Set BROWSER_NAME desired capability. It's Android in our case here.
		  //capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
	      //Set android VERSION desired capability. Set your mobile device's OS version.
		  //capabilities.setCapability(CapabilityType.VERSION, "4.4.2");
		  //capabilities.setCapability(CapabilityType.VERSION, "6.0.1");
		

		  // Set android platformName desired capability. It's Android in our case here
		  // Set android appPackage desired capability. It is
		  // com.android.calculator2 for calculator application.
		  // Set your application's appPackage if you are using any other app.
		  capabilities.setCapability("appPackage", "com.android.launcher3");
		  System.out.println("5");
		  //capabilities.setCapability("appPackage", "com.android.launcher");

		  // Set android appActivity desired capability. It is
		  // com.android.calculator2.Calculator for calculator application.
		  // Set your application's appPackage if you are using any other app.
		 
		  capabilities.setCapability("appActivity", "com.android.launcher3.Launcher");
		  
		  //capabilities.setCapability("appActivity", "com.android.launcher2.Launcher");

		  //capabilities.setCapability("appActivity", "com.android.email.activity.setup.AccountSetupFinal");
		  //Created object of RemoteWebDriver will all set capabilities.
		  //Set appium server address and port number in URL string.
		  //It will launch calculator app in android device.
		   driver = new RemoteWebDriver(new URL(appiumServiceUrl), capabilities);
		  try
		  {
		  //driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		  }
		  catch(Exception e )
		  {
			  System.out.println(e);
		  }
		  System.out.println("out1");
		  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		  wait=new WebDriverWait(driver, 300);
		  System.out.println("out");
		  
		  }
		  switchcontrol++;
		  
		  
		  
		  
		 //driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		  SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
	 	  Calendar RC = Calendar.getInstance();
	 	  String start=DF.format(RC.getTime());
	 	  System.out.println(start);
	 	  String stratdate[]=start.split("-");
	 	  int date=Integer.parseInt(stratdate[0]);
	 	  datestr=String.valueOf(date);
	     
	      userlogin(username,Password,searchStatus,CustName,Custcode);
	     //BulkRateUpdate(rateStatus);
	     //BulkInvUpdate(invStatus);
	     //BulkResUpdate(resStatus);
	      driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'rates_updates')]")).click(); 
	  		 RNA=RatePresentCheck();
		    	if(RNA)
		    	{
		    		execute=false;
		    		System.out.println("Rate Not available");
		    		Reporter.log("<font font-family='Times New Roman'>Calendar Page--</font><font color='red'>FAIL(Rates Not Available)</font></a>", true);
		    		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='More options']")).click();
				    driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'title') and @text='Logout']")).click();
		    		
		    		
		    	}
		    	if(execute)
		    	{//rate and inventory update button
	  		    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	}
	     SlectedrateUpdate(rateStatus);
	     selectedInvUpdate(invStatus);
	     selectedResUpdate(resStatus); 
	     RateDateRangeUpdate(rateStatus);
	     InvDateRange(invStatus);
	     ResDateRange(resStatus);
	     logout();	
	     //System.out.println("LogOut");
	      
	     
	     
		 
		
	 }
	 
	 
	  
	  @DataProvider
	  public static Object[][] data()
	  {
		  randomNum = rand.nextInt((150-1) + 1) + 1;

		 // String superuser=EncryDecry.Encryption("Bangalore@777");
		  
		 // String crsdemo=EncryDecry.Encryption("Reznext@123");
		  //String iosrez=EncryDecry.Encryption("Reznext@123");
		  //String sachin=EncryDecry.Encryption("Reznext@123");
		  /*Object[][] datavalue={ 
				                {"superuser@reznext.com",Encryption("Bangalore@777"),"1","1","1","1","The Pearl","77777"},
				                {"crsdemouser",Encryption("Reznext@123"),"1","1","1","1","W Maldives","103653"},
				                {"ios@reznext.com",Encryption("Reznext@123"),"0","1","1","1","Nayara Springs","103638"},
				                {"sachin.upadhyay@goldentulipindia.com",Encryption("Reznext@123"),"2","0","0","0","Golden Tulip Manali","714"}
				               };*/
		  
	  Object[][] datavalue={ 
	                {"superuser@reznext.com",Encryption("Test@321"),"1","1","1","1","Breeze","3241"},
	                {"anil.kumarPA@reznext.com",Encryption("Test@123"),"0","1","1","1","Hotel Poornima","5162"},
	                {"reznextuser@reznext.com",Encryption("Test@123"),"1","0","0","0","Breeze","3241"},
	                {"poornimagroup@reznext.com",Encryption("Test@123"),"1","1","1","1","Hotel Poornima","5162"}
	               };

		  return datavalue;
	  }
	  
	  public static void logindata()
	  {
		 
	  }
	  
	    public static void userlogin(String Username,char[] password,String searchStatus,String CustName,String CustCode) throws InterruptedException, AWTException
	    {
	    	try
	    	{
	    		driver.findElement(By.xpath("//android.widget.TextView[@index='3']")).click();
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}
	  	    Thread.sleep(2000);
	  	    driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_username')]")).sendKeys(Username);
	  	  
	  	    String passdec=decryption(password);
	  	   
	  	    driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_password')]")).sendKeys(passdec);
	  	    driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_login')]")).click();
	  	    custsearch(Username,searchStatus,CustName,CustCode);
	    }
	    
	    public static void custsearch(String Username,String SearchStatus,String CustName,String Custcode) throws InterruptedException, AWTException
	    {
	  		 //Search and selecting property 
	  		
	  		 if(SearchStatus.equalsIgnoreCase("1"))
	  		 {
	  		 int custSearch=0;
	  		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[contains(@resource-id,'et_selectproperty_search')]")));
	  		 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_selectproperty_search')]")).sendKeys(Custcode);
	  		 WebElement layout=driver.findElement(By.xpath("//android.widget.FrameLayout"));
	  		 Thread.sleep(2000);
	  		 List<WebElement> searchlayout=driver.findElements(By.xpath("//android.widget.TextView[contains(@resource-id,'thumbnail')]"));
	  		 System.out.println(searchlayout.size());
	  		 for(WebElement e:searchlayout)
	  		 {
	  			 if(e.getText().equalsIgnoreCase(CustName))
	  			 {
	  				 e.click();
	  				Reporter.log("<font font-family='Times New Roman'>Customer Search--</font><font color='blue' >PASS</font></a>", true);
	  				 custSearch=1;
	  			 }
	  		 }
	  		 if(custSearch==0)
	  		 {
	  			Reporter.log("<font font-family='Times New Roman'>Customer Search--</font><font color='red'>FAIL</font></a>", true);
	  			//Reporter.log("Customer Search--FAIL");
	  		 }
	  		 }
	  		 if(SearchStatus.equalsIgnoreCase("2"))
	  		 {
	  			 int GroupCustSearch=0;
	  		
	  			 int count=0;
	  			 boolean flag=true;
	  			 LinkedHashSet<String>custname=new LinkedHashSet<String>();
	  			
	  			 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[contains(@resource-id,'et_selectproperty_search')]")));
	  			
				 String val[]={};
				 String val1[]={};
				 if(Username.equalsIgnoreCase("sachin.upadhyay@goldentulipindia.com"))
				 {
				   val=goldengroup;	 
				   val1=goldengroupnot;
				 }
	  			 for(String str:val)
	  			 {
	  				 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_selectproperty_search')]")).sendKeys(str);
	  				 List<WebElement> custno=driver.findElements(By.xpath("//android.widget.TextView[contains(@resource-id,'thumbnail') and @index='0']"));
	  				 if(custno.size()!=0)
	  				 {
	  					 count++;
	  				 }
	  			
	  			 }
	  			 for(String str:val1)
	  			 {
	  				 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_selectproperty_search')]")).sendKeys(str);
	  				 List<WebElement> custno=driver.findElements(By.xpath("//android.widget.TextView[contains(@resource-id,'thumbnail') and @index='0']"));
	  				 if(custno.size()!=0)
	  				 {
	  					 count++;
	  				 } 
	  				
	  			 }
	  			if(count==17)
	  			{
	  				Reporter.log("<font font-family='Times New Roman'>GroupLogin Customer Count--</font><font color='blue'>PASS</font></a>", true);
	  				//Reporter.log("GroupLogin Customer Count--PASS");
	  			}
	  			else
	  			{
	  				Reporter.log("<font font-family='Times New Roman'>GroupLogin Customer Count--</font><font color='red'>FAIL</font></a>", true);
	  				//Reporter.log("GroupLogin Customer Count--FAIL");
	  			}
	  			System.out.println(count);
	  			
	  			
	  			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[contains(@resource-id,'et_selectproperty_search')]")));
		  		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_selectproperty_search')]")).sendKeys(Custcode);
		  		WebElement layout=driver.findElement(By.xpath("//android.widget.FrameLayout"));
		  		Thread.sleep(2000);
		  		List<WebElement> searchlayout=driver.findElements(By.xpath("//android.widget.TextView[contains(@resource-id,'thumbnail')]"));
		  		System.out.println(searchlayout.size());
		  		for(WebElement e:searchlayout)
		  		{
		  			 if(e.getText().equalsIgnoreCase(CustName))
		  			 {
		  				 e.click();
		  				Reporter.log("<font font-family='Times New Roman'>GroupLogin Customer Search--</font><font color='blue'>PASS</font></a>", true);
		  				 //Reporter.log("GroupLogin Customer Search--PASS");
		  				GroupCustSearch=1;
		  				 
		  			 }
		  		 }
	  			  if(GroupCustSearch==0)
	  			  {
	  				//Reporter.log("GroupLogin Customer Search--FAIL");
	  				Reporter.log("<font font-family='Times New Roman'>GroupLogin Customer Search--</font><font color='red'>FAIL</font></a>", true);
	  				//Reporter.log("<font color='red'>GroupLogin Customer Search--FAIL</font></a>", true);
	  			  }
	  		 }
	  		 //Statistics page validation check
	  		 int[] staticindex={0,4,23,2};
	  		 int staticcount=0;
	  		 String[] staticvalue={"ARR","RNS","LOS","PACE"};
	  		 for(int i=0;i<staticindex.length;i++)
	  		 {
	  			 String actualvalue="";
	  			 int val=staticindex[i];
	  			 String strvalue=staticvalue[i];
	  			 if(val==0)
	  			 {
	  			     actualvalue=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'textView')]")).getText();
	  			 }
	  			 else if(val==2)
	  			 
	  			 {
	  				 actualvalue=driver.findElement(By.xpath("//android.widget.TextView[@text='PACE']")).getText();
	  			 }
	  			 else
	  			 {
	  				 actualvalue=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'textView"+val+"')]")).getText();
	  			 }
	  			 
	  			 if(strvalue.equals(actualvalue))
	  			 {
	  				// System.out.println(strvalue);
	  				 staticcount++;
	  			 }
	  		 }
	  		 if(staticcount==4)
	  	     {
	  		  //System.out.println("Statistic page pass");
	  		  Reporter.log("<font font-family='Times New Roman'>Statistic Page--</font><font color='blue'>PASS</font></a>", true);
	  		  //Reporter.log("Statistic page pass--PASS");
	  	     }
	  		 else
	  		 {
	  			Reporter.log("<font font-family='Times New Roman'>Statistic Page--</font><font color='red'>FAIL</font></a>", true);
	  			//Reporter.log("<font color='red'>Statistic page pass--FAIL</font></a>", true);
	  			//Reporter.log("Statistic page pass--FAIL");
	  		 }
	  		 
	  		 
	  		 //calendar page checks 
	  	   
	  		/* driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'rates_updates')]")).click(); 
 RNA=RatePresentCheck();
		    	if(RNA)
		    	{
		    		execute=false;
		    		System.out.println("Rate Not available");
		    		Reporter.log("<font font-family='Times New Roman'>Calendar Page--</font><font color='red'>FAIL(Rates Not Available)</font></a>", true);
		    		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='More options']")).click();
				    driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'title') and @text='Logout']")).click();
		    		
		    		
		    	}
		    	if(execute)
		    	{//rate and inventory update button
	  		    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	}*/
	  		 //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
	  		 //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
	  		// Thread.sleep(10000);
	    	
	    }
	    
	    
	    
	

	    public static void  BulkRateUpdate(String rateStatus) throws InterruptedException, AWTException
	    {
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[contains(@resource-id,'ratess') and @index='0']")));
	    	
	    	if(rateStatus.equalsIgnoreCase("1"))
	    	 {
	    	
	    	for(int i=1;i<=4;i++)
	    	{
	    	 switch(i)
	    	 {
	    	 case 1:
	    	 driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'ratess') and @index='0']")).click();
		     Bulkratecheck=1;
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")));
		     Bulkratecheck=2;
	    	 calendarselection(); 
	    	 
	    	
			 //Update single and double rates,extra child and extra adult
	    	 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'selcted_rates') and @text='Select Rate Code']")).click();
	    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[contains(@resource-id,'button2') and @text='Done']")));
	    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button2') and @text='Done']")).click();
			 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'open_room') and @text='+']")).click();
			 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'values') and @text='0.0']")).click();
			 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'rate_data')]")).sendKeys("100");
			 //driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'rate_data') and @text='100']")).sendKeys(Keys.ENTER);
			 //click on update rate button 
			// for(int p=0;p<3;p++)
			 //{
			 robot =new Robot();
			 Thread.sleep(2000);
			 robot.keyPress(KeyEvent.VK_CONTROL);
			 robot.keyPress(KeyEvent.VK_BACK_SPACE); 
			 robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			 robot.keyRelease(KeyEvent.VK_CONTROL);
			 Thread.sleep(2000);
			 //}
	    	System.out.println("Anil kumar-------------------------------------------------------------------------------------------------------");
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")));
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")));
			  driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")).click();
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")));
		     String RateStatus=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")).getText();
		     if(RateStatus.equalsIgnoreCase("\"Your bulk Rate request has been processed!\""))
		     {
		    	  Reporter.log("<font font-family='Times New Roman'>Bulk Range Rate Update with combination(+ , 0.0)--</font><font color='blue'>PASS</font></a>", true);
		    	  //Reporter.log("Slected Date Rate Update--PASS");
		    	  Thread.sleep(1000);
		    	  driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
		     }
		     else
		     {
		    	 Reporter.log("<font font-family='Times New Roman'>>Bulk Range Rate Update with combination(+ , 0.0)--</font><font color='red'>FAIL("+RateStatus+")</font></a>", true);
		    	// Reporter.log("<font color='red'>Slected Date Rate Update--FAIL("+RateStatus+")</font></a>", true);
		    	 Thread.sleep(1000);
		    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
		     }
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[contains(@resource-id,'ratess') and @index='0']")));
			 break;
	    	 case 2:
	    		 driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'ratess') and @index='0']")).click();
			     Bulkratecheck=1;
			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")));
			     Bulkratecheck=2;
		    	 calendarselection(); 
		    	 
		    	
				 //Update single and double rates,extra child and extra adult
		    	 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'selcted_rates') and @text='Select Rate Code']")).click();
		    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[contains(@resource-id,'button2') and @text='Done']")));
		    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button2') and @text='Done']")).click();
				 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'close_room') and @text='-']")).click();
				 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'values') and @text='0.0']")).click();
				 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'rate_data')]")).sendKeys("100");
				// driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'rate_data')]")).click();
				 //click on update rate button 
				 
				 robot =new Robot();
				 Thread.sleep(2000);
				 robot.keyPress(KeyEvent.VK_CONTROL);
				 robot.keyPress(KeyEvent.VK_BACK_SPACE);
				 robot.keyRelease(KeyEvent.VK_CONTROL);
				 robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				 Thread.sleep(4000);
				 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")));
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")));
				 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")).click();
				 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")));
			     String RateStatus1=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")).getText();
			     if(RateStatus1.equalsIgnoreCase("\"Your bulk Rate request has been processed!\""))
			     {
			    	  Reporter.log("<font font-family='Times New Roman'>>Bulk Range Rate Update with combination(- , 0.0)--</font><font color='blue'>PASS</font></a>", true);
			    	  //Reporter.log("Slected Date Rate Update--PASS");
			    	  Thread.sleep(1000);
			    	  driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
			     }
			     else
			     {
			    	 Reporter.log("<font font-family='Times New Roman'>>Bulk Range Rate Update with combination(- , 0.0)--</font><font color='red'>FAIL("+RateStatus1+")</font></a>", true);
			    	// Reporter.log("<font color='red'>Slected Date Rate Update--FAIL("+RateStatus+")</font></a>", true);
			    	 Thread.sleep(1000);
			    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
			     }
			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[contains(@resource-id,'ratess') and @index='0']")));
			     break;
	    	 case 3:
	    		 driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'ratess') and @index='0']")).click();
			     Bulkratecheck=1;
			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")));
			     Bulkratecheck=2;
		    	 calendarselection(); 
		    	 
		    	
				 //Update single and double rates,extra child and extra adult
		    	 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'selcted_rates') and @text='Select Rate Code']")).click();
		    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[contains(@resource-id,'button2') and @text='Done']")));
		    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button2') and @text='Done']")).click();
				 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'open_room') and @text='+']")).click();
				 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'percetage') and @text='%']")).click();
				 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'rate_data')]")).sendKeys("20");
				 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'rate_data')]")).click();
				 //click on update rate button 
			
				 robot =new Robot();
				 Thread.sleep(2000);
				 robot.keyPress(KeyEvent.VK_CONTROL);
				 robot.keyPress(KeyEvent.VK_BACK_SPACE);
				 robot.keyRelease(KeyEvent.VK_CONTROL);
				 robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				 Thread.sleep(2000);
				 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")));
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")));
				 
			     driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")).click();
				 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")));
			     String RateStatus2=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")).getText();
			     if(RateStatus2.equalsIgnoreCase("\"Your bulk Rate request has been processed!\""))
			     {
			    	  Reporter.log("<font font-family='Times New Roman'>>Bulk Range Rate Update with combination(+ , %)--</font><font color='blue'>PASS</font></a>", true);
			    	  //Reporter.log("Slected Date Rate Update--PASS");
			    	  Thread.sleep(1000);
			    	  driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
			     }
			     else
			     {
			    	 Reporter.log("<font font-family='Times New Roman'>>Bulk Range Rate Update with combination(+ , %)--</font><font color='red'>FAIL("+RateStatus2+")</font></a>", true);
			    	// Reporter.log("<font color='red'>Slected Date Rate Update--FAIL("+RateStatus+")</font></a>", true);
			    	 Thread.sleep(1000);
			    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
			     }
			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[contains(@resource-id,'ratess') and @index='0']")));
			     break;
	    	 case 4:
	    		 driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'ratess') and @index='0']")).click();
			     Bulkratecheck=1;
			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")));
			     Bulkratecheck=2;
		    	 calendarselection(); 
		    	 
		    	
				 //Update single and double rates,extra child and extra adult
		    	 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'selcted_rates') and @text='Select Rate Code']")).click();
		    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[contains(@resource-id,'button2') and @text='Done']")));
		    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button2') and @text='Done']")).click();
				 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'close_room') and @text='-']")).click();
				 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'percetage') and @text='%']")).click();
				 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'rate_data')]")).sendKeys("10");
				 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'rate_data')]")).click();
				 //click on update rate button 
				 robot =new Robot();
				 Thread.sleep(2000);
				 robot.keyPress(KeyEvent.VK_CONTROL);
				 robot.keyPress(KeyEvent.VK_BACK_SPACE);
				 robot.keyRelease(KeyEvent.VK_CONTROL);
				 robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				 Thread.sleep(2000);
				 
				 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")));
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")));
				 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates')]")).click();
				 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")));
			     String RateStatus3=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")).getText();
			     if(RateStatus3.equalsIgnoreCase("\"Your bulk Rate request has been processed!\""))
			     {
			    	  Reporter.log("<font font-family='Times New Roman'>>Bulk Range Rate Update with combination(- , %)--</font><font color='blue'>PASS</font></a>", true);
			    	  //Reporter.log("Slected Date Rate Update--PASS");
			    	  Thread.sleep(1000);
			    	  driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
			     }
			     else
			     {
			    	 Reporter.log("<font font-family='Times New Roman'>>Bulk Range Rate Update with combination(- , %)--</font><font color='red'>FAIL("+RateStatus3+")</font></a>", true);
			    	// Reporter.log("<font color='red'>Slected Date Rate Update--FAIL("+RateStatus+")</font></a>", true);
			    	 Thread.sleep(1000);
			    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
			     }
			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[contains(@resource-id,'ratess') and @index='0']")));
			     break;
	    		 
	    		 
	    	 } 
	    	 //driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_start_selected_date')]")).clear();
	    	
	         //driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_end_selected_date')]")).clear();
	    	 //driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'tv_end_selected_date')]")).sendKeys(end);
	    	}
	    	 }
	    
	    
	    		
	    }

        
	    public static void BulkInvUpdate(String invStatus) throws InterruptedException, AWTException
	    {
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[contains(@resource-id,'inventory')]")));
	    	if(invStatus.equalsIgnoreCase("1"))
	    	 {
			 driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'inventory')]")).click();
			 calendarselection();
			 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'selcted_rates_inventory') and @text='Select Rate Code']")).click();
	    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[contains(@resource-id,'button2') and @text='Done']")));
	    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button2') and @text='Done']")).click();
	    	 List<WebElement>TotalRoom=driver.findElements(By.xpath("//android.widget.EditText"));
	    	 System.out.println(TotalRoom.size());
	    	 for(WebElement e:TotalRoom)
	    	 {
	    		 e.sendKeys("20");
	    	 }
	   
			 /*robot =new Robot();
			 robot.keyPress(KeyEvent.VK_CONTROL);
			 robot.keyPress(KeyEvent.VK_BACK_SPACE);
			 robot.keyRelease(KeyEvent.VK_CONTROL);
			 robot.keyRelease(KeyEvent.VK_BACK_SPACE);*/
			 
			 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates') and @text='Update Bulk INVEntory']")).click();
			 
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")));
		     String InventoryStatus=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")).getText();
		     if(InventoryStatus.startsWith("\"Your bulk Inventory request has been processed!\""))
		     {
		          Reporter.log("<font font-family='Times New Roman'>Bulk Inventory Update--</font><font color='blue'>PASS</font></a>", true);
		    	  //Reporter.log("Selected Date Inventory Update--PASS");
		    	  driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
		     }
		     else if(InventoryStatus.equalsIgnoreCase("Your request cannot be processed. Your property is set up on Realtime inventory updates and hence your inventory is processed through your PMS. If you would like to change your inventory set up to allocation, please reach us at 080 4545 4014 or email us at techhelp@reznext.com"))
		     {
		    	 Reporter.log("<font font-family='Times New Roman'>Bulk Inventory Inventory Update--</font><font color='green'>PASS(RealTime Inventory)</font></a>", true);
		    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
		    	 
		     }
		     else
		     {
		    	 Reporter.log("<font font-family='Times New Roman'>Bulk Inventory Update--</font><font color='red'>FAIL("+InventoryStatus+")</font></a>", true);
		    	 //Reporter.log("<font color='red'>Selected Date Inventory Update--FAIL("+InventoryStatus+")</font></a>", true);
		    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
		     }
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[contains(@resource-id,'ratess')]")));
		     
	    	 }
			 }
	    	
	    
	    public static void BulkResUpdate(String resStatus) throws AWTException, InterruptedException
	    {
	       
	    	for(int i=0;i<2;i++)
			{
	    		 if(resStatus.equalsIgnoreCase("1"))
	    		 {
	    			
	    		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[contains(@resource-id,'stopcells')]")));
			     driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'stopcells')]")).click();
			     calendarselection();
			     driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'selcted_rates') and @text='Select Rate Code']")).click();
		    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[contains(@resource-id,'button2') and @text='Done']")));
		    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button2') and @text='Done']")).click();
			     if(i==0)
			     {
			    	
			    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'close_room_restriction') and @text='Close Room']")).click();
			     }
			     else
			     {
			    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'open_room_restriction') and @text='Open Room']")).click();
			     }
			    
			    /* robot =new Robot();
				 robot.keyPress(KeyEvent.VK_CONTROL);
				 robot.keyPress(KeyEvent.VK_BACK_SPACE);
				 robot.keyRelease(KeyEvent.VK_CONTROL);
				 robot.keyRelease(KeyEvent.VK_BACK_SPACE);*/
				 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates') and @text='Update Bulk RESTRICTION']")));
			     driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button_done_rates') and @text='Update Bulk RESTRICTION']")).click();	
			     wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")));
			     String restrictionStatus=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")).getText();
			     if(restrictionStatus.startsWith("Your Request has been Processed to"))
			     {
			    	  Reporter.log("<font font-family='Times New Roman'>Selected Date Restriction Update--</font><font color='blue'>PASS</font></a>", true);
			    	  //Reporter.log("Selected Date Restriction Update--PASS");
			    	  Thread.sleep(2000);
			    	  driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
			     }
			     else
			     {
			    	 Reporter.log("<font font-family='Times New Roman'>Selected Date Restriction Update--</font><font color='red'>FAIL("+restrictionStatus+")</font></a>", true);
			    	 Thread.sleep(2000);
			    	 //Reporter.log("<font color='red'>Selected Date Restriction Update--FAIL("+restrictionStatus+")</font></a>", true);
			    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
			     }
			     //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='14']")));
		  		 //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
			     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[contains(@resource-id,'stopcells')]")));
			 } 
	    		 
	    		 
	      }
	    	 
	    	}
	    
	    
	    
		public static void SlectedrateUpdate(String rateStatus) throws AWTException, InterruptedException
	    {
		/*	try
			{
	  		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
			}
			catch(Exception e)
			{
				
				
				 RNA=true;
				 System.out.println(e);
				
			}
			if(RNA)
			{
				logout();
				execute=false;
			}*/
            //RNA=RatePresentCheck();
	    	/*if(RNA)
	    	{
	    		System.out.println("Rate Not available");
	    		logout();
	    		execute=false;
	    		
	    	}*/
			
			 //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
	  		 //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
			
			if(execute)
			{
				RNA=RatePresentCheck();
		    	if(RNA)
		    	{
		    		execute=false;
		    		System.out.println("Rate Not available");
		    		Reporter.log("<font font-family='Times New Roman'>Selected Date Rate Update--</font><font color='red'>FAIL(Rates Not Available)</font></a>", true);
		    		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='More options']")).click();
				    driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'title') and @text='Logout']")).click();
		    		
		    		
		    	}
			 if(execute)	
			 {
	    	 if(rateStatus.equalsIgnoreCase("1"))
	    	 {
	    	
			 int[] day={18,19};
			 for(int d :day)
			 {
			 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+d+"']")).click();
			
			 }
			 
			 //update Rates
			 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'saverates') and @text='Rate']")).click();
			 //Update single and double rates,extra child and extra adult
			 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'single_rate')]")).sendKeys("1000");
			 
			 //driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'double_rate')]")).sendKeys("6024");
			 //driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'extra_child')]")).sendKeys("505");
			 //driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'extra_adult')]")).sendKeys("1005");
			 
			 //clickon select channel icon button
			 driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'icon_edit')]")).click();
			 
			 //click on update rate button 
			 robot =new Robot();
			 Thread.sleep(2000);
			 robot.keyPress(KeyEvent.VK_CONTROL);
			 robot.keyPress(KeyEvent.VK_BACK_SPACE);
			 robot.keyRelease(KeyEvent.VK_CONTROL);
			 robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			 if(rateStatus.equalsIgnoreCase("1"))
			 {
			     driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button_done')]")).click();
			     wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")));
			     String RateStatus=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")).getText();
			     if(RateStatus.equalsIgnoreCase("Your Request has been Processed Successfully"))
			     {
			    	  Reporter.log("<font font-family='Times New Roman'>Selected Date Rate Update--</font><font color='blue'>PASS</font></a>", true);
			    	  //Reporter.log("Slected Date Rate Update--PASS");
			    	  Thread.sleep(2000);
			    	  driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
			     }
			     else
			     {
			    	 Reporter.log("<font font-family='Times New Roman'>Selected Date Rate Update--</font><font color='red'>FAIL("+RateStatus+")</font></a>", true);
			    	 //Reporter.log("<font color='red'>Slected Date Rate Update--FAIL("+RateStatus+")</font></a>", true);
			    	 Thread.sleep(2000);
			    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
			     }
			     
	  		     wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
			    //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
	  		    //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
		 
			 }
			 else if(rateStatus.equalsIgnoreCase("0"))
			 {
				   
			 }
	    	 }
			 }
	    }
	    }
	    
	    

		
		
		
	    public static void selectedInvUpdate(String invStatus) throws AWTException, InterruptedException
	    {
	    	
	    	/* try
	    	 {
	  		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	 }
	    	 catch(Exception e)
	    	 {
	    		 
	    		 logout();
	    		 if(RNA)
	    		 {
	    			 RNA=false;
	    		 Reporter.log("<font font-family='Times New Roman'>Rates Not Available</font><font color='red'>FAIL</font></a>", true);
	    		 }
	    	 }*/
	    	
	    	/*if(RNA)
	    	{
	    		execute=false;
	    	}*/
	    	 //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
	    	 //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
			  if(execute)
			  {
				  RNA=RatePresentCheck();
			    	if(RNA)
			    	{
			    		execute=false;
			    		System.out.println("Rate Not available");
			    		Reporter.log("<font font-family='Times New Roman'>Selected Date Inventory Update--</font><font color='red'>FAIL(Rates Not Available)</font></a>", true);
			    		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='More options']")).click();
					    driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'title') and @text='Logout']")).click();
			    		
			    		
			    	}
			 if(execute)
			 {
	    	 if(invStatus.equalsIgnoreCase("1"))
	    	 {
			 for(int d :invday)
			 {
			 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+d+"']")).click();
			
			 }
			 
			 //update Inventory
			 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'saveinventory') and @text='Inventory']")).click();
			 
			 // enter inventory in text
			 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'Invenroty_data')]")).sendKeys("10");
			 
			 
	         //click on select channel icon button
			 driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'icon')]")).click();
	    	 
			 robot =new Robot();
			 robot.keyPress(KeyEvent.VK_CONTROL);
			 robot.keyPress(KeyEvent.VK_BACK_SPACE);
			 robot.keyRelease(KeyEvent.VK_CONTROL);
			 robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'update_inventory')]")).click();
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")));
		     String InventoryStatus=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")).getText();
		     if(InventoryStatus.startsWith("Your Request has been Processed to"))
		     {
		    	  Reporter.log("<font font-family='Times New Roman'>Selected Date Inventory Update--</font><font color='blue'>PASS</font></a>", true);
		    	  Thread.sleep(2000);
		    	  //Reporter.log("Selected Date Inventory Update--PASS");
		    	  driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
		     }
		     else if(InventoryStatus.equalsIgnoreCase("Your request cannot be processed. Your property is set up on Realtime inventory updates and hence your inventory is processed through your PMS. If you would like to change your inventory set up to allocation, please reach us at 080 4545 4014 or email us at techhelp@reznext.com"))
		     {
		    	 
		    	 Reporter.log("<font font-family='Times New Roman'>Selected Date Inventory Update--</font><font color='green'>PASS(RealTime Inventory)</font></a>", true);
		    	 Thread.sleep(2000);
		    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
		    	 
		     }
		     else
		     {
		    	 Reporter.log("<font font-family='Times New Roman'>Selected Date Inventory Update--</font><font color='red'>FAIL("+InventoryStatus+")</font></a>", true);
		    	 Thread.sleep(2000);
		    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
		     }
	  		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
			 //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
	  		 //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
		    
			 }
			
			  }
			  }
	    }
			 
	    
	    
	    
	    
	    public static void selectedResUpdate(String resStatus) throws InterruptedException, AWTException
	    {
	    	//"Your bulk Restriction request has been processed!"
	    	/*try
	    	{
	  		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	}
	    	 catch(Exception e)
	    	 {
	    		 
	    		 logout();
	    		 if(RNA)
	    		 {
	    			 RNA=false;
	    		 Reporter.log("<font font-family='Times New Roman'>Rates Not Available</font><font color='red'>FAIL</font></a>", true);
	    		 }
	    	 }*/
	    	 //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
	    	if(execute)
	    	{
	    		RNA=RatePresentCheck();
		    	if(RNA)
		    	{
		    		execute=false;
		    		System.out.println("Rate Not available");
		    		Reporter.log("<font font-family='Times New Roman'>Selected Date Restriction Update--</font><font color='red'>FAIL(Rates Not Available)</font></a>", true);
		    		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='More options']")).click();
				    driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'title') and @text='Logout']")).click();
		    		
		    		
		    	}
	        if(execute)
	        {
	    	for(int i=0;i<2;i++)
			{
	    		 if(resStatus.equalsIgnoreCase("1"))
	    		 {
	    			
				 for(int d :invday)
				 {
				 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+d+"']")).click();
				
				 }
			     driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'saverestriction') and @text='Restriction']")).click();
			     if(i==0)
			     {
			    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'close_room') and @text='Close Room']")).click();
			     }
			     else
			     {
			    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'open_room') and @text='Open Room']")).click();
			     }
			     driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'edit_icon')]")).click();
			    
			     driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'update_restriction')]")).click();	
			     wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")));
			     String restrictionStatus=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")).getText();
			    //"Your bulk Restriction request has been processed!"
			     if(restrictionStatus.startsWith("Your Request has been Processed to"))
			     {
			    	  Reporter.log("<font font-family='Times New Roman'>Selected Date Restriction Update--</font><font color='blue'>PASS</font></a>", true);
			    	  //Reporter.log("Selected Date Restriction Update--PASS");
			    	  Thread.sleep(2000);
			    	  driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
			     }
			     else
			     {
			    	 Reporter.log("<font font-family='Times New Roman'>Selected Date Restriction Update--</font><font color='red'>FAIL("+restrictionStatus+")</font></a>", true);
			    	 Thread.sleep(2000);
			    	 //Reporter.log("<font color='red'>Selected Date Restriction Update--FAIL("+restrictionStatus+")</font></a>", true);
			    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
			     }
			     //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='14']")));
		  		 //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
		  		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
		    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
			     
			 } 
	    		 
	    		 
	      }
	    	 
	    	}
	    	}
	    }
	    
	    
	    public static void RateDateRangeUpdate(String rateStatus) throws InterruptedException, AWTException
	    {
	    /*	try
	    	{
	  		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	}
	    	catch(Exception e)
	    	 {
	    		 
	    		 logout();
	    		 if(RNA)
	    		 {
	    			 RNA=false;
	    		 Reporter.log("<font font-family='Times New Roman'>Rates Not Available</font><font color='red'>FAIL</font></a>", true);
	    		 }
	    	 }*/
	    	 //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='14']")));
	  		 //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
	    	if(execute)
	    	{ 
	    		RNA=RatePresentCheck();
		    	if(RNA)
		    	{
		    		execute=false;
		    		System.out.println("Rate Not available");
		    		Reporter.log("<font font-family='Times New Roman'>Date Range Rate Update--</font><font color='red'>FAIL(Rates Not Available)</font></a>", true);
		    		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='More options']")).click();
				    driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'title') and @text='Logout']")).click();
		    		
		    		
		    	}
	    	if(execute)
	    	{
	    	if(rateStatus.equalsIgnoreCase("1"))
	    	 {
	    	 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'saverates') and @text='Rate']")).click();
	    	 calendarselection(); 
	    	 
	    	
			 //Update single and double rates,extra child and extra adult
			 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'single_rate')]")).sendKeys("1000");
			 //driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'double_rate')]")).sendKeys("6500");
			 
			 //driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'extra_child')]")).sendKeys("510");
			 //driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'extra_adult')]")).sendKeys("1009");
			 //driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'occupancy') and @text='OTHER OCCUPANCY RATES ']")).click();
			 //driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_triplerate')]")).sendKeys("7500");
			 //driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'et_quadrate')]")).sendKeys("8500");
			/* robot =new Robot();
			 robot.keyPress(KeyEvent.VK_CONTROL);
			 robot.keyPress(KeyEvent.VK_BACK_SPACE);
			 robot.keyRelease(KeyEvent.VK_CONTROL);
			 robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'Quin_rate')]")).sendKeys("9000");
			 robot =new Robot();
			 robot.keyPress(KeyEvent.VK_CONTROL);
			 robot.keyPress(KeyEvent.VK_BACK_SPACE);
			 robot.keyRelease(KeyEvent.VK_CONTROL);
			 robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'Sixt')]")).sendKeys("9500");*/
			 
			 //clickon select channel icon button
			 
			 driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'icon_edit')]")).click();
			 
			 //click on update rate button 
			  Thread.sleep(2000);
			  robot =new Robot();
			  robot.keyPress(KeyEvent.VK_CONTROL);
			  robot.keyPress(KeyEvent.VK_BACK_SPACE);
			  robot.keyRelease(KeyEvent.VK_CONTROL);
			 robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			
			 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button_done')]")).click();
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")));
		     String RateStatus=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")).getText();
		     if(RateStatus.equalsIgnoreCase("Your Request has been Processed Successfully"))
		     {
		    	  Reporter.log("<font font-family='Times New Roman'>Date Range Rate Update--</font><font color='blue'>PASS</font></a>", true);
		    	  Thread.sleep(2000);
		    	  //Reporter.log("Slected Date Rate Update--PASS");
		    	  driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
		     }
		     else
		     {
		    	 Reporter.log("<font font-family='Times New Roman'>Date Range Rate Update--</font><font color='red'>FAIL("+RateStatus+")</font></a>", true);
		    	 Thread.sleep(2000);
		    	 // Reporter.log("<font color='red'>Slected Date Rate Update--FAIL("+RateStatus+")</font></a>", true);
		    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
		     }
			 //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='14']")));
	  		 //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
	  		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
			
	    	 } 
	    	 //driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_start_selected_date')]")).clear();
	    	
	         //driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_end_selected_date')]")).clear();
	    	 //driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'tv_end_selected_date')]")).sendKeys(end);
	    	}
	    	}
	    }
	    
	    
	    public static void InvDateRange(String invStatus) throws AWTException, InterruptedException
	    {
	       /*  try
	         {
	  		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	         }
	         catch(Exception e)
	    	 {
	    		 
	    		 logout();
	    		 if(RNA)
	    		 {
	    			 RNA=false;
	    		 Reporter.log("<font font-family='Times New Roman'>Rates Not Available</font><font color='red'>FAIL</font></a>", true);
	    		 }
	    	 }*/
	    	 //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='14']")));
	  		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
			 
	    	 //update Inventory
	    	if(execute)
	    	{
	    		
	    		RNA=RatePresentCheck();
		    	if(RNA)
		    	{
		    		execute=false;
		    		System.out.println("Rate Not available");
		    		Reporter.log("<font font-family='Times New Roman'>Date Range Inventory Update--</font><font color='red'>FAIL(Rates Not Available)</font></a>", true);
		    		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='More options']")).click();
				    driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'title') and @text='Logout']")).click();
		    		
		    		
		    	}
		    	if(execute)
		    	{
	    	 if(invStatus.equalsIgnoreCase("1"))
	    	 {
			 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'saveinventory') and @text='Inventory']")).click();
			 calendarselection();
			 // enter inventory in text
			 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'Invenroty_data')]")).sendKeys("10");
			 
			 
	         //click on select channel icon button
			 driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'icon')]")).click();
	    	 
			 robot =new Robot();
			 robot.keyPress(KeyEvent.VK_CONTROL);
			 robot.keyPress(KeyEvent.VK_BACK_SPACE);
			 robot.keyRelease(KeyEvent.VK_CONTROL);
			 robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'update_inventory')]")).click();
			 
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")));
		     String InventoryStatus=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")).getText();
		     if(InventoryStatus.startsWith("Your Request has been Processed to"))
		     {
		          Reporter.log("<font font-family='Times New Roman'>Date Range Inventory Update--</font><font color='blue'>PASS</font></a>", true);
		          Thread.sleep(2000);
		          //Reporter.log("Selected Date Inventory Update--PASS");
		    	  driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
		     }
		     else if(InventoryStatus.equalsIgnoreCase("Your request cannot be processed. Your property is set up on Realtime inventory updates and hence your inventory is processed through your PMS. If you would like to change your inventory set up to allocation, please reach us at 080 4545 4014 or email us at techhelp@reznext.com"))
		     {
		    	 Reporter.log("<font font-family='Times New Roman'>Selected Date Inventory Update--</font><font color='green'>PASS(RealTime Inventory)</font></a>", true);
		    	 Thread.sleep(2000);
		    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
		    	 
		     }
		     else
		     {
		    	 Reporter.log("<font font-family='Times New Roman'>Date Range Inventory Update--</font><font color='red'>FAIL("+InventoryStatus+")</font></a>", true);
		    	 Thread.sleep(2000);
		    	 //Reporter.log("<font color='red'>Selected Date Inventory Update--FAIL("+InventoryStatus+")</font></a>", true);
		    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
		     }
			 //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='14']")));
	  		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
		     
	    	 }
			 }
	    	}
			
	    }
	    
	    public static void ResDateRange(String resStatus) throws InterruptedException, AWTException
	    {
	   
	    	for(int i=0;i<2;i++)
			 {
	    		 /*try
	    		 {
		  		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
		    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	    		 }
	    		 catch(Exception e)
		    	 {
		    		 
		    		 logout();
		    		 if(RNA)
		    		 {
		    			 RNA=false;
		    		 Reporter.log("<font font-family='Times New Roman'>Rates Not Available</font><font color='red'>FAIL</font></a>", true);
		    		 }
		    	 }*/
		    	 //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
	    		 //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='14']")));
	    		if(execute)
	    		{
	    			RNA=RatePresentCheck();
			    	if(RNA)
			    	{
			    		execute=false;
			    		System.out.println("Rate Not available");
			    		Reporter.log("<font font-family='Times New Roman'>Date Range Restriction Update--</font><font color='red'>FAIL(Rates Not Available)</font></a>", true);
			    		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='More options']")).click();
					    driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'title') and @text='Logout']")).click();
			    		
			    		
			    	}
			    	if(execute)
			    	{
	    		 
	    		if(resStatus.equalsIgnoreCase("1"))
	    		 {
	    			 
	    			 
	    		
			     driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'saverestriction') and @text='Restriction']")).click();
			     if(i==0)
			     {
			    	 calendarselection();
			    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'close_room') and @text='Close Room']")).click();
			     }
			     else
			     {
			    	 calendarselection();
			    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'open_room') and @text='Open Room']")).click();
			     }
			     driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'edit_icon')]")).click();
			     
				/* robot =new Robot();
				 robot.keyPress(KeyEvent.VK_CONTROL);
				 robot.keyPress(KeyEvent.VK_BACK_SPACE);
				 robot.keyRelease(KeyEvent.VK_CONTROL);
				 robot.keyRelease(KeyEvent.VK_BACK_SPACE);*/
			     driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'update_restriction')]")).click();
			     wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")));
			     String RestrictionStatus=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")).getText();
			     if(RestrictionStatus.startsWith("Your Request has been Processed to"))
			     {
			    	  Reporter.log("<font font-family='Times New Roman'>Date Range Restriction Update--</font><font color='blue'>PASS</font></a>", true);
			    	  
			    	  Thread.sleep(2000);//Reporter.log("Selected Date Restriction Update--PASS");
			    	  driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
			     }
			     else
			     {
			    	 Reporter.log("<font font-family='Times New Roman'>Date Range Restriction Update--</font><font color='red'>FAIL("+RestrictionStatus+")</font></a>", true);
			    	 Thread.sleep(2000);
			    	 //Reporter.log("<font color='red'>Selected Date Restriction Update--FAIL("+RestrictionStatus+")</font></a>", true);
			    	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
			     }
		  		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
		    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
			     //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='14']")));
		  		 //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'dateTitle')]")));
			     
			   } 
	    		 
	        }
	    		}
			 }
	    }
	    
	    public static void logout()
	    {

		     //Logout 
	    	if(execute)
	    	{
		     driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='More options']")).click();
		     driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'title') and @text='Logout']")).click();
	    	}
	    	
	    }
	    
	    public static void calendarselection() throws InterruptedException
	    {
	     	  SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
	 	      Calendar RC = Calendar.getInstance();
	 	      RC.add(Calendar.DATE, 1);
	 	      String start=DF.format(RC.getTime());
	 	      System.out.println(start);
	 	      String stratdate[]=start.split("-");
	 	      RC.add(Calendar.DATE, 20);
	 	      String end=DF.format(RC.getTime());
	 	  
	 	      String enddate[]=end.split("-");
	 	      int stmonth=Integer.parseInt(stratdate[1]);
	 	      int enmonth=Integer.parseInt(enddate[1]);
	 	     //Start Date
	 	      driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'iv_start_calendar')]")).click();
	 	      Thread.sleep(2000);
	 	    
	 	      driver.findElement(By.xpath("//android.view.View[@index='0']")).click();
	 	      String expectedStartdate=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'date_picker_header_date')]")).getAttribute("text");
	 	      System.out.println(expectedStartdate);
	 	      String expStartDate[]=expectedStartdate.split(" ");
	 	      ActualStartdate=expStartDate[1];
	 	      System.out.println("Expected date  "+month[stmonth]);
	 	      System.out.println("Actual Date   "+ActualStartdate);
	 	     while(!(month[stmonth].equalsIgnoreCase(ActualStartdate)))
	 	    {
	 	    	  System.out.println("inside"+ActualStartdate);
	 	          driver.findElement(By.xpath("//android.widget.ImageButton[contains(@resource-id,'next')]")).click();
	 	          driver.findElement(By.xpath("//android.view.View[@index='0']")).click();
	 	          expectedStartdate=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'date_picker_header_date')]")).getAttribute("text");
	 	          String expStartDate1[]=expectedStartdate.split(" ");
	 	          ActualStartdate=expStartDate1[1];
	 	        
	 	    }
	 	          System.out.println(stratdate[0]+" "+monthdesc[stmonth]+" "+stratdate[2]);
	 	    	  driver.findElement(By.xpath("//android.view.View[contains(@content-desc,'"+stratdate[0]+" "+monthdesc[stmonth]+" "+stratdate[2]+"')]")).click();
	 	          driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
	 	    	
	 	          //end date 
		 	      driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'iv_end_calendar')]")).click();
		 	      Thread.sleep(2000);
		 	    
		 	      driver.findElement(By.xpath("//android.view.View[@index='0']")).click();
		 	      String  expectedEnddate=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'date_picker_header_date')]")).getAttribute("text");
		 	      System.out.println(expectedEnddate);
		 	      String expEndDate[]=expectedEnddate.split(" ");
		 	      ActualEnddate=expEndDate[1];
		 	      System.out.println("Expected date  "+month[enmonth]);
		 	      System.out.println("Actual Date   "+ActualEnddate);
		 	     while(!(month[enmonth].equalsIgnoreCase(ActualEnddate)))
		 	    {
		 	    	 System.out.println("inside"+ActualEnddate);
		 	         driver.findElement(By.xpath("//android.widget.ImageButton[contains(@resource-id,'next')]")).click();
		 	         driver.findElement(By.xpath("//android.view.View[@index='0']")).click();
		 	         expectedEnddate=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'date_picker_header_date')]")).getAttribute("text");
		 	         String expEndDate1[]=expectedEnddate.split(" ");
		 	         ActualEnddate=expEndDate1[1];
		 	        
		 	    }
		 	         System.out.println(enddate[0]+" "+monthdesc[enmonth]+" "+enddate[2]);
		 	    	 driver.findElement(By.xpath("//android.view.View[contains(@content-desc,'"+enddate[0]+" "+monthdesc[enmonth]+" "+enddate[2]+"')]")).click();
		 	         driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
		 	    	
	    }
	    
	    public static char[] Encryption(String value)
		{
		
			String pass=value;
			char[]c=pass.toCharArray();
		    System.out.println("random number"+randomNum);
			char enc[]=new char[c.length];
			//char dec[]=new char[c.length];
			int eni=0;
			for(char c1:c)
			{
				int val=(int)c1+randomNum;
				System.out.println(String.valueOf(c1)+"---"+val);
				enc[eni]=(char)val;
				eni++;
				
			}
			
			String encrString=String.valueOf(enc);
			System.out.println("encr--"+encrString);
			return enc;
		}
		public static String decryption(char[] value)
		{
			int dei=0;
			
			
			char dec[]=new char[value.length];
			for(char c1:value)
			{
				int val=(int)c1-randomNum;
				dec[dei]=(char)val;
				dei++;
				
			}
			String decryValue=String.valueOf(dec);
			System.out.println("desc--"+decryValue);
			return decryValue;
		}
		
public  void copyfile() throws IOException
{
    System.out.println("sunil");
	SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
    Calendar RC = Calendar.getInstance();
    String start=DF.format(RC.getTime());
    File source=new File("C:/Users/anil.kumar/Desktop/test-output/Suite/Test.html");
    System.out.println("anil");
	//File source = new File("D:/WorkSpace/ibv4_code/MobileTest/test-output/AndroidMobileAutomation/Mobile Android Automation.html");
	File dest = new File("D:/Android Daily report/AndroidReport-"+start+".html");
	System.out.println(start);
	try
	{
	Files.copy(source.toPath(), dest.toPath());
	}
	catch(Exception e)
	{
		
	}
	    //org.apache.commons.io.FileUtils.copyDirectory(source, dest);
	
	}

public  void  ibv4()
{try
{
	SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
    Calendar RC = Calendar.getInstance();
    String start=DF.format(RC.getTime());
   Properties props = new Properties();
   props.put("mail.smtp.host", "smtp.gmail.com");
   props.put("mail.smtp.socketFactory.port", "465");
   props.put("mail.smtp.socketFactory.class",
   "javax.net.ssl.SSLSocketFactory");
   props.put("mail.smtp.auth", "true");
   props.put("mail.smtp.port", "465");
   Session session = Session.getDefaultInstance(props,

   new javax.mail.Authenticator() {
   protected PasswordAuthentication getPasswordAuthentication() {
   return new PasswordAuthentication("anil.kumar@reznext.com","@nilk844@");
}
});
   
       // Create a default MimeMessage object.
       Message message = new MimeMessage(session);

       // Set From: header field of the header.
       message.setFrom(new InternetAddress("anil.kumar@reznext.com"));

       // Set To: header field of the header.
       message.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse("poornima.rao@reznext.com"));

       // Set Subject: header field
       message.setSubject("Android Mobile Daily Health Status");

       // Create the message part
       BodyPart messageBodyPart = new MimeBodyPart();

       // Now set the actual message
       messageBodyPart.setText("Android Mobile Daily Health Status");

       // Create a multipar message
       Multipart multipart = new MimeMultipart();

       // Set text message part
       multipart.addBodyPart(messageBodyPart);

       // Part two is attachment
       messageBodyPart = new MimeBodyPart();
       String filename = "D:/Android Daily report/AndroidReport-"+start+".html";
       DataSource source = new FileDataSource(filename);
       messageBodyPart.setDataHandler(new DataHandler(source));
       messageBodyPart.setFileName(filename);
       multipart.addBodyPart(messageBodyPart);

       // Send the complete message parts
       message.setContent(multipart);

       // Send message
       Transport.send(message);

       System.out.println("Sent message successfully....");

    } catch (MessagingException e) {
       throw new RuntimeException(e);
    }


 }
public static boolean RatePresentCheck()
{
	int val=0;
	try
	{
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@resource-id,'name_image') and @text='"+datestr+"']")));
	 
	 RNA=false;
	 val=1;
	 return RNA;
	}
	finally {
		if(val==0)
		{
	      return true;
	    }
}
}
protected void finalize() throws IOException

{
	copyfile();
	ibv4();
// finalization code here

}
}




