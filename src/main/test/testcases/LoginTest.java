package testcases;

import java.util.Hashtable;

import net.bytebuddy.implementation.bytecode.Throw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bdhillon.TestBase.TestBase;

public class LoginTest extends TestBase {

	public static Assert asrt;

	@Test(dataProvider = "getData")
	public void LoginTest(Hashtable<String, String> data) {
		try {
		if (data.get("RunMode").equalsIgnoreCase("N")) {
			logger.info("Test Case is Skipped");
			throw new SkipException("Test Case Skipped");
		}

		logger = extent.createTest(" TC01_LoginTest " + data);

		selectBrowser(data.get("Browser"));

		logger.info("********** Starting Test Case **********");

		

		fluentWait("LoginEmail_tagName");

		getElement("LoginEmail_tagName").sendKeys(data.get("Username"));

		logger.info("Entered the Username of the logger.Username is : "
				+ data.get("Username"));

		getElement("nxtbtn_css").click();

		logger.info("Clicking on the next button. ");

		fluentWait("LoginPassword_css");
		waitinSec(3);
		getElement("LoginPassword_css").sendKeys(data.get("Password"));

		TakeScreenshot();

		waitinSec(2);

		logger.info("Entered the Password of the logger.");

		getElement("SignBtn_css").click();

		

		

			fluentWait("waitingElement_xpath");
			String title = getTitle();
			boolean cond = title.contains("Inbox");
	

			if (cond) {
				
				TakeScreenshot();
				waitinSec(1);
				asrt.assertEquals(true, cond);
				logger.pass("User Logged In Successfully.");
			} else{
				TakeScreenshot();
				logger.fail("User is not logged in Successfully");
				asrt.fail();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			
			logger.info(" Exception Occured." );
			TakeScreenshot();
			logger.fail("User is not logged in Successfully");
			asrt.fail("Exception Occured");
			
		}

		logger.info("********** Ending Test Case **********");

	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = com.bdhillon.ReadExcel.DataUtil.getData(reader,
				"LoginTest");
		return data;
	}

	@AfterMethod
	public void quite() {

		if (extent != null) {
			logger.info(" Closing Driver ");
			extent.flush();
			driver.quit();

		}

	}

	@BeforeTest
	public void init() {

		if (prop == null) {
			TestBase tb = new TestBase();
			tb.init();
		}
	}
}
