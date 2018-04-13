package testcases;

import java.util.Hashtable;

import net.bytebuddy.implementation.bytecode.Throw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bdhillon.TestBase.TestBase;

public class LoginTest extends TestBase{
	
	
public static SoftAssert asrt;
	
	@Test (dataProvider="getData")
	public void LoginTest(Hashtable <String,String> data){
	
		if(data.get("RunMode").equalsIgnoreCase("N")){
			logger.info("Test Case is Skipped"  );
			throw new SkipException("Test Case Skipped");
		}
		
		logger=extent.createTest(" TC01_LoginTest "+data);
		
		selectBrowser(data.get("Browser"));
		
		logger.info("********** Starting Test Case **********");
		
		driver.get(prop.getProperty("Application_URL"));
		
		fluentWait("LoginEmail_tagName");
		getElement("LoginEmail_tagName").sendKeys(data.get("Username"));
		
		logger.info("Entered the Username of the logger.Username is : " + data.get("Username"));
		
		getElement("nxtbtn_css").click();
		waitinSec(3);
		
		logger.info("Clicking on the next button. ");
		
		fluentWait("LoginPassword_css");
		
		getElement("LoginPassword_css").sendKeys(data.get("Password"));
		
		TakeScreenshot();
		
		waitinSec(2);
		
		logger.info("Entered the Password of the logger.");
		
		getElement("SignBtn_css").click();
		
		waitinSec(3);
		
		String title=driver.getTitle();
		fluentWait("waitingElement_css");
		boolean cond=title.contains("Gmail");
		System.out.println(cond);
		asrt.assertEquals(true,cond );
		
		logger.info("********** Ending Test Case **********");
		
	}
	
	
	@DataProvider
	public Object[][] getData() {
	Object[][] data = com.bdhillon.ReadExcel.DataUtil.getData(reader,
			"LoginTest");
	return data;
	}
	
	
	@AfterSuite
	public void quite(){
	
		if(extent!=null){
			logger.info(" Closing Driver ");			
			extent.flush();
			driver.quit();
			
		}
	
		}
	
	@BeforeSuite
	public void init(){
		
		if(prop==null){
			TestBase tb=new TestBase();
			tb.init();
		}
	}
}
	

